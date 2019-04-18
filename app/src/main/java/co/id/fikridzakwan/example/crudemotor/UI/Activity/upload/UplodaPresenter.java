package co.id.fikridzakwan.example.crudemotor.UI.Activity.upload;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;

import java.io.File;

import co.id.fikridzakwan.example.crudemotor.Data.remote.ApiClient;
import co.id.fikridzakwan.example.crudemotor.Data.remote.ApiInterface;
import co.id.fikridzakwan.example.crudemotor.Model.motor.MotorResponse;
import co.id.fikridzakwan.example.crudemotor.Model.upload.UploadResponse;
import co.id.fikridzakwan.example.crudemotor.Utilts.Constants;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UplodaPresenter implements UploadConstract.Presenter {

    private final UploadConstract.View view;
    private ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);

    public UplodaPresenter(UploadConstract.View view) {
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
                        view.showSpinnerCategory(response.body().getMotorDataList());
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
    public void uploadMotor(Context context, Uri filePath, String namaMotor, String descMotor, String idKategory) {
        view.showProgress();

        if (namaMotor.isEmpty()) {
            view.hideProgress();
            view.showMessage("Nama motor harus id isi");
            return;
        }

        if (descMotor.isEmpty()) {
            view.hideProgress();
            view.showMessage("Deskription harus di isi");
            return;
        }

        if (filePath == null) {
            view.hideProgress();
            view.showMessage("Pilih gambar telebih dahulu");
            return;
        }

        File myFile = new File(filePath.getPath());
        Uri selectedImage = getImageContentUri(context, myFile, filePath);
        File imageFile = null;

        if (selectedImage != null) {
            String partImage = getPath(context, selectedImage);
            imageFile = new File(partImage);
        } else {
            String partImage = getPath(context, filePath);
            imageFile = new File(partImage);
        }

        SharedPreferences pref = context.getSharedPreferences(Constants.pref_name, 0);
        String idUser = pref.getString(Constants.KEY_USER_ID,"");

        RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), imageFile);
        MultipartBody.Part mPartImage = MultipartBody.Part.createFormData("image", imageFile.getName(), requestBody);

        RequestBody mNamaMotor = RequestBody.create(MediaType.parse("multipart/form-data"), namaMotor);
        RequestBody mDescMotor = RequestBody.create(MediaType.parse("multipart/form-data"), descMotor);

        Call<UploadResponse> call = apiInterface.uploadMotor(
                Integer.valueOf(idUser),
                Integer.valueOf(idKategory),
                mNamaMotor,
                mDescMotor,
                mPartImage);
        call.enqueue(new Callback<UploadResponse>() {
            @Override
            public void onResponse(Call<UploadResponse> call, Response<UploadResponse> response) {
                view.hideProgress();

                if (response.body() != null) {
                    if (response.body().getResult() == 1) {
                        view.showMessage(response.body().getMessage());
                        view.successUpload();
                    } else {
                        view.showMessage(response.body().getMessage());
                    }
                } else {
                    view.showMessage("Data kosong");
                }
            }

            @Override
            public void onFailure(Call<UploadResponse> call, Throwable t) {
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
