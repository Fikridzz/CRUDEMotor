package co.id.fikridzakwan.example.crudemotor.UI.Fragment.home;

import co.id.fikridzakwan.example.crudemotor.Data.remote.ApiClient;
import co.id.fikridzakwan.example.crudemotor.Data.remote.ApiInterface;
import co.id.fikridzakwan.example.crudemotor.Model.motor.MotorResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomePresenter implements HomeConstract.Presenter {

    private ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
    private final HomeConstract.View view;

    public HomePresenter(HomeConstract.View view) {
        this.view = view;
    }

    @Override
    public void getListKategoryMotor() {
        view.showProgress();

        Call<MotorResponse> call = apiInterface.getMotorKategory();
        call.enqueue(new Callback<MotorResponse>() {
            @Override
            public void onResponse(Call<MotorResponse> call, Response<MotorResponse> response) {
                view.hideProgress();

                if (response.body() != null) {
                        view.showMotorListKategory(response.body().getMotorDataList());
                    } else {
                        view.showFailurMessage("Data kosong");
                }
            }

            @Override
            public void onFailure(Call<MotorResponse> call, Throwable t) {
                view.hideProgress();
                view.showFailurMessage(t.getMessage());
            }
        });
    }

    @Override
    public void getListPopulerMotor() {

    }

    @Override
    public void getListNewsmotor() {
        view.showProgress();

        Call<MotorResponse> call = apiInterface.getMotorNews();
        call.enqueue(new Callback<MotorResponse>() {
            @Override
            public void onResponse(Call<MotorResponse> call, Response<MotorResponse> response) {
                view.hideProgress();

                if (response.body() != null) {
                    if (response.body().getResult() == 1) {
                        view.showMotorListNews(response.body().getMotorDataList());
                        view.showFailurMessage(response.body().getMessage());
                    }
                } else {
                    view.showFailurMessage("Data kosong");
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
