package co.id.fikridzakwan.example.crudemotor.UI.Activity.detailbyuser;

import android.content.Context;

import java.io.File;

import co.id.fikridzakwan.example.crudemotor.Data.remote.ApiClient;
import co.id.fikridzakwan.example.crudemotor.Data.remote.ApiInterface;
import co.id.fikridzakwan.example.crudemotor.Model.detail.DetailMotorResponse;
import co.id.fikridzakwan.example.crudemotor.Model.motor.MotorResponse;
import co.id.fikridzakwan.example.crudemotor.UI.Activity.detailmotor.DetailMotorConstract;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailByUserPresenter implements DetailByUserConstract.Presenter {

    private final DetailByUserConstract.View view;
    private ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);

    public DetailByUserPresenter(DetailByUserConstract.View view) {
        this.view = view;
    }

    @Override
    public void getDetailMotor(String idMotor) {
        view.showProgress();

        Call<DetailMotorResponse> call = apiInterface.getDetailMotor(Integer.valueOf(idMotor));
        call.enqueue(new Callback<DetailMotorResponse>() {
            @Override
            public void onResponse(Call<DetailMotorResponse> call, Response<DetailMotorResponse> response) {
                view.hideProgress();

                if (response.body() != null) {
                    if (response.body().getResult() == 1) {
                        view.showDetailMotor(response.body().getMotorData());
                    } else {
                        view.showFailurMessage(response.body().getMessage());
                    }
                } else {
                    view.showFailurMessage("Data kosong");
                }
            }

            @Override
            public void onFailure(Call<DetailMotorResponse> call, Throwable t) {
                view.hideProgress();
                view.showFailurMessage(t.getMessage());
            }
        });
    }

    @Override
    public void deleteDetailMotor(String idMotor, String namaFotoMotor) {
        view.showProgress();

        if (idMotor.isEmpty()) {
            view.showMessage("ID Motor tidak ada");
            return;
        }

        if (namaFotoMotor.isEmpty()) {
            view.showMessage("Nama foto motor tidak ada");
            return;
        }

        Call<MotorResponse> call = apiInterface.deleteMotor(Integer.valueOf(idMotor), namaFotoMotor);
        call.enqueue(new Callback<MotorResponse>() {
            @Override
            public void onResponse(Call<MotorResponse> call, Response<MotorResponse> response) {
                view.hideProgress();

                if (response.body() != null) {
                    if (response.body().getResult() == 1) {
                        view.showMessage(response.body().getMessage());
                        view.successDelete();
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
                view.showFailurMessage(t.getMessage());
            }
        });
    }
}
