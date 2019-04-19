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
import co.id.fikridzakwan.example.crudemotor.Model.motor.MotorData;
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
            Log.i("cek", "getDetailMotor: masuk " + idMotor);
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
    public void updateDataMotor(Context context, String namaMotor, String descMotor, String idKategory, String idMotor) {
        view.showProgress();

        if (namaMotor.isEmpty()) {
            view.hideProgress();
            view.showMessage("Nama motor kosong");
            return;
        }

        if (descMotor.isEmpty()) {
            view.hideProgress();
            view.showMessage("Desc kosong");
        }

        Call<MotorResponse> call = apiInterface.updateMotor(
                Integer.valueOf(idMotor),
                Integer.valueOf(idKategory),
                namaMotor,
                descMotor);
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
}
