package co.id.fikridzakwan.example.crudemotor.UI.Activity.editbyuser;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;

import java.io.File;

import co.id.fikridzakwan.example.crudemotor.Data.remote.ApiClient;
import co.id.fikridzakwan.example.crudemotor.Data.remote.ApiInterface;
import co.id.fikridzakwan.example.crudemotor.Model.detail.DetailMotorResponse;
import co.id.fikridzakwan.example.crudemotor.Model.motor.MotorResponse;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditByUserPresenter implements EditByUserConstract.Presenter {

    private final EditByUserConstract.View view;
    private ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
    private File imageFile = null;

    public EditByUserPresenter(EditByUserConstract.View view) {
        this.view = view;
    }

    @Override
    public void getKategory() {
        view.showProgress();

        Call<MotorResponse> call = apiInterface.getMotorKategory();
        call.enqueue(new Callback<MotorResponse>() {
            @Override
            public void onResponse(Call<MotorResponse> call, Response<MotorResponse> response) {
                view.hideProgress();

                if (response.body() != null) {
                    if (response.body().getResult() == 1) {
                        view.showSpinnerKategory(response.body().getMotorDataList());
                    } else {
                        view.showMessage(response.body().getMessage());
                    }
                } else {
                    view.showMessage("Data kosong");
                }
            }

            @Override
            public void onFailure(Call<MotorResponse> call, Throwable t) {
                view.hideProgress();
                view.showMessage(t.getMessage());
            }
        });
    }

    @Override
    public void getDetailMotor(String idMotor) {
        view.showProgress();

        if (idMotor.isEmpty()) {
            view.hideProgress();
            view.showMessage("ID Motor tidak ada");
            return;
        }

        Call<DetailMotorResponse> call = apiInterface.getDetailMotor(Integer.valueOf(idMotor));
        call.enqueue(new Callback<DetailMotorResponse>() {
            @Override
            public void onResponse(Call<DetailMotorResponse> call, Response<DetailMotorResponse> response) {
                view.hideProgress();

                if (response.body() != null) {
                    if (response.body().getResult() == 1) {
                        view.showDetailMotor(response.body().getMotorData());
                    } else {
                        view.showMessage(response.body().getMessage());
                    }
                } else {
                    view.showMessage("Data kosong");
                }
            }

            @Override
            public void onFailure(Call<DetailMotorResponse> call, Throwable t) {
                view.hideProgress();
                view.showMessage(t.getMessage());
            }
        });
    }

    @Override
    public void updateDataMotor(Context context, Uri filePath, String namaMotor, String descMotor, String idKategory, String namaFotoMotor, String idMotor) {
        view.showProgress();

        if (namaMotor.isEmpty()) {
            view.hideProgress();
            view.showMessage("Nama motor kosong");
            return;
        }

        if (descMotor.isEmpty()) {
            view.hideProgress();
            view.showMessage("Desc motor kosong");
            return;
        }

        if (filePath != null) {
            File myFile = new File(filePath.getPath());
            Uri selectedImage = getImageContentUri(context, myFile, filePath);
            String partImage = getPath(context, selectedImage);
            imageFile = new File(partImage);
        }

        RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), imageFile);
        MultipartBody.Part mPartImage = MultipartBody.Part.createFormData("image", imageFile.getName(), requestBody);

        RequestBody mNamaMotor = RequestBody.create(MediaType.parse("multipart/form-data"), namaMotor);
        RequestBody mDescMotor = RequestBody.create(MediaType.parse("multipart/form-data"), descMotor);
        RequestBody mNamaFotoMotor = RequestBody.create(MediaType.parse("multipart/form-data"), namaFotoMotor);

        Call<MotorResponse> call = apiInterface.updateMotor(
                Integer.valueOf(idMotor),
                Integer.valueOf(idKategory),
                mNamaMotor,
                mDescMotor,
                mNamaFotoMotor,
                mPartImage);
        call.enqueue(new Callback<MotorResponse>() {
            @Override
            public void onResponse(Call<MotorResponse> call, Response<MotorResponse> response) {
                view.hideProgress();

                if (response.body() != null) {
                    if (response.body().getResult() == 1) {
                        view.showMessage(response.body().getMessage());
                        view.successUpdate();
                    } else {
                        view.showMessage(response.body().getMessage());
                    }
                } else {
                    view.showMessage("Data kurang");
                }
            }

            @Override
            public void onFailure(Call<MotorResponse> call, Throwable t) {
                view.hideProgress();
                view.showMessage(t.getMessage());
            }
        });
    }

    private String getPath(Context context, Uri filepath) {
        Cursor cursor = context.getContentResolver().query(filepath, null, null, null, null);
        cursor.moveToFirst();
        String document_id = cursor.getString(0);
        document_id = document_id.substring(document_id.lastIndexOf(":") + 1);
        cursor.close();

        cursor = context.getContentResolver().query(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                null, MediaStore.Images.Media._ID + " = ? ",
                new String[]{document_id}, null);
        cursor.moveToFirst();
        String path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
        cursor.close();

        return path;
    }

    private Uri getImageContentUri(Context context, File imageFile, Uri filePath) {
        String fileAbsolutePath = imageFile.getAbsolutePath();
        Cursor cursor = context.getContentResolver().query(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                new String[]{MediaStore.Images.Media._ID},
                MediaStore.Images.Media.DATA + "=? ",
                new String[]{fileAbsolutePath}, null);

        if (cursor != null && cursor.moveToFirst()) {
            // Apabila file gambar sudah pernah diapakai namun ada kondisi lain yang belum diketahui
            // Apabila file gambar sudah pernah dipakai pengambilan bukan di galery

            Log.i("Isi Selected if", "Masuk cursor ada");
            return filePath;

        } else {
            Log.i("Isi Selected else", "cursor tidak ada");
            if (imageFile.exists()) {
                // Apabila file gambar baru belum pernah di pakai
                Log.i("Isi Selected else", "imagefile exists");
                ContentValues values = new ContentValues();
                values.put(MediaStore.Images.Media.DATA, fileAbsolutePath);
                return context.getContentResolver().insert(
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
            } else {
                // Apabila file gambar sudah pernah dipakai
                // Apabila file gambar sudah pernah dipakai di galery
                Log.i("Isi Selected else", "imagefile tidak exists");
                return null;
            }
        }
    }
}
