package co.id.fikridzakwan.example.crudemotor.UI.Activity.User.register;

import android.util.Log;

import co.id.fikridzakwan.example.crudemotor.Data.remote.ApiClient;
import co.id.fikridzakwan.example.crudemotor.Data.remote.ApiInterface;
import co.id.fikridzakwan.example.crudemotor.Model.login.LoginData;
import co.id.fikridzakwan.example.crudemotor.Model.login.LoginResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterPresenter implements RegisterConstract.Presenter {

    private final RegisterConstract.View view;
    private ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);

    public RegisterPresenter(RegisterConstract.View view) {
        this.view = view;
    }

    @Override
    public void doRegisterUser(LoginData loginData) {
        if (loginData != null) {
            if (!loginData.getNamauser().isEmpty() &&
                    !loginData.getAlamat().isEmpty() &&
                    !loginData.getNotelp().isEmpty() &&
                    !loginData.getUsername().isEmpty() &&
                    !loginData.getPassword().isEmpty() &&
                    !loginData.getJenkel().isEmpty() &&
                    !loginData.getLevel().isEmpty()) {

                view.showProgress();
                Call<LoginResponse> call = apiInterface.registerUser(
                        loginData.getNamauser(),
                        loginData.getAlamat(),
                        loginData.getNotelp(),
                        loginData.getUsername(),
                        loginData.getPassword(),
                        loginData.getJenkel(),
                        loginData.getLevel());
                call.enqueue(new Callback<LoginResponse>() {
                    @Override
                    public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                        view.hideProgress();

                        if (response.body() != null) {
                            if (response.body().getResult() == 1) {
                                view.showRegisterSuccess(response.body().getMessage());
                            } else {
                                view.showError(response.body().getMessage());
                            }
                        } else {
                            view.showError("Data kosong");
                        }
                    }

                    @Override
                    public void onFailure(Call<LoginResponse> call, Throwable t) {
                        view.hideProgress();
                        view.showError(t.getMessage());
                    }
                });
            } else {
                view.showError("Tidak ada yang boleh kososong");
            }
        }
    }
}
