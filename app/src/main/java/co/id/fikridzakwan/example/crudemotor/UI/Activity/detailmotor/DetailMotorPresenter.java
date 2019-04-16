package co.id.fikridzakwan.example.crudemotor.UI.Activity.detailmotor;

import co.id.fikridzakwan.example.crudemotor.Data.remote.ApiClient;
import co.id.fikridzakwan.example.crudemotor.Data.remote.ApiInterface;
import co.id.fikridzakwan.example.crudemotor.Model.detail.DetailMotorResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailMotorPresenter implements DetailMotorConstract.Presenter {

    private final DetailMotorConstract.View view;
    private ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);

    public DetailMotorPresenter(DetailMotorConstract.View view) {
        this.view = view;
    }

    @Override
    public void getDetailMotor(String idMotor) {
        view.showProgress();

        if (idMotor.isEmpty()) {
            view.showFailureMessage("ID motor tidak ada");
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
                        view.showFailureMessage(response.body().getMessage());
                    }
                } else {
                    view.showFailureMessage("Data kosong");
                }
            }

            @Override
            public void onFailure(Call<DetailMotorResponse> call, Throwable t) {
                view.hideProgress();
                view.showFailureMessage(t.getMessage());
            }
        });
    }
}
