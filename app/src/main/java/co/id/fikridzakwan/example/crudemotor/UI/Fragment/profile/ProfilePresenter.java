package co.id.fikridzakwan.example.crudemotor.UI.Fragment.profile;

import android.content.Context;
import android.content.SharedPreferences;

import co.id.fikridzakwan.example.crudemotor.Data.remote.ApiClient;
import co.id.fikridzakwan.example.crudemotor.Data.remote.ApiInterface;
import co.id.fikridzakwan.example.crudemotor.Model.login.LoginData;
import co.id.fikridzakwan.example.crudemotor.Model.motor.MotorData;
import co.id.fikridzakwan.example.crudemotor.Model.motor.MotorResponse;
import co.id.fikridzakwan.example.crudemotor.Utilts.Constants;
import co.id.fikridzakwan.example.crudemotor.Utilts.SessionManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfilePresenter implements ProfileConstract.Presenter {

    private SharedPreferences pref;
    private final ProfileConstract.View view;
    private ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);

    public ProfilePresenter(ProfileConstract.View view) {
        this.view = view;
    }

    @Override
    public void getDataUser(Context context) {
        pref = context.getSharedPreferences(Constants.pref_name, 0);

        LoginData loginData = new LoginData();

        loginData.setUsername(pref.getString(Constants.KEY_USER_USERNAME, ""));

        view.showDataUser(loginData);
    }

    @Override
    public void getMotorListByUser(String idUser) {
        if (idUser.isEmpty()) {
            view.showFailurMessage("ID user tidak ada");
            return;
        }

        Call<MotorResponse> call = apiInterface.getMotorByUser(Integer.valueOf(idUser));
        call.enqueue(new Callback<MotorResponse>() {
            @Override
            public void onResponse(Call<MotorResponse> call, Response<MotorResponse> response) {
                if (response.body() != null) {
                    if (response.body().getResult() == 1) {
                        view.showMotorListByUser(response.body().getMotorDataList());
                    } else {
                        view.showFailurMessage(response.body().getMessage());
                    }
                } else {
                    view.showFailurMessage("Data kosong");
                }
            }

            @Override
            public void onFailure(Call<MotorResponse> call, Throwable t) {
                view.showFailurMessage(t.getMessage());
            }
        });
    }

    @Override
    public void logoutSesion(Context context) {
        SessionManager sessionManager = new SessionManager(context);
        sessionManager.logout();
    }
}
