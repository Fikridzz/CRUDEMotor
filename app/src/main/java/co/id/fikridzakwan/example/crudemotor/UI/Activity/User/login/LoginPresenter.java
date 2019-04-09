package co.id.fikridzakwan.example.crudemotor.UI.Activity.User.login;

import android.content.Context;

import co.id.fikridzakwan.example.crudemotor.Data.remote.ApiClient;
import co.id.fikridzakwan.example.crudemotor.Data.remote.ApiInterface;
import co.id.fikridzakwan.example.crudemotor.Model.login.LoginData;
import co.id.fikridzakwan.example.crudemotor.Model.login.LoginResponse;
import co.id.fikridzakwan.example.crudemotor.Utilts.SessionManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginPresenter implements LoginConstract.Presenter {

    private final LoginConstract.View view;
    private ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
    private SessionManager mSessionManager;

    public LoginPresenter(LoginConstract.View view) {
        this.view = view;
    }

    @Override
    public void doLogin(String username, String password) {
        if (username.isEmpty()) {
            view.usernameError("Username kosong");
            return;
        }

        if (password.isEmpty()) {
            view.passwordError("Password kosong");
            return;
        }

        view.showProgress();

        Call<LoginResponse> call = apiInterface.loginUser(username, password);
        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                view.hideProgress();

                if (response.body() != null) {
                    if (response.body().getResult() == 1) {
                        if (response.body().getData() != null) {
                            view.loginSuccess(response.body().getMessage(), response.body().getData());
                        } else {
                            view.loginFailure("No data");
                        }
                    } else {
                        view.loginFailure(response.body().getMessage());
                    }
                } else {
                    view.loginFailure("No data");
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                view.hideProgress();
                view.loginFailure(t.getMessage());
            }
        });
    }

    @Override
    public void saveDataUser(Context context, LoginData loginData) {
        mSessionManager.isLogin();
        mSessionManager.createSesion(loginData);
    }

    @Override
    public void cekLogin(Context context) {
        mSessionManager = new SessionManager(context);
        Boolean isLogin = mSessionManager.isLogin();
        if (isLogin) {
            view.isLogin();
        }
    }
}
