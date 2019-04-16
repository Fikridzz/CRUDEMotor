package co.id.fikridzakwan.example.crudemotor.UI.Activity.bykategory;

import co.id.fikridzakwan.example.crudemotor.Data.remote.ApiClient;
import co.id.fikridzakwan.example.crudemotor.Data.remote.ApiInterface;
import co.id.fikridzakwan.example.crudemotor.Model.motor.MotorResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MotorByKategoryPresenter implements MotorByKategoryConstract.Presenter {

    private final MotorByKategoryConstract.View view;
    private ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);

    public MotorByKategoryPresenter(MotorByKategoryConstract.View view) {
        this.view = view;
    }

    @Override
    public void getListMotorByKategory(String idKategory) {
        view.showProgress();

        if (idKategory.isEmpty()) {
            view.showFailureMessage("ID kategori tidak ada");
            return;
        }

        Call<MotorResponse> call = apiInterface.getMotorByKategory(Integer.valueOf(idKategory));
        call.enqueue(new Callback<MotorResponse>() {
            @Override
            public void onResponse(Call<MotorResponse> call, Response<MotorResponse> response) {
                view.hideProgress();

                if (response.body() != null) {
                    if (response.body().getResult() == 1) {
                        view.showListMotorByKategory(response.body().getMotorDataList());
                    }
                } else {
                    view.showFailureMessage("Data kosong");
                }
            }

            @Override
            public void onFailure(Call<MotorResponse> call, Throwable t) {
                view.hideProgress();
                view.showFailureMessage(t.getMessage());
            }
        });
    }
}
