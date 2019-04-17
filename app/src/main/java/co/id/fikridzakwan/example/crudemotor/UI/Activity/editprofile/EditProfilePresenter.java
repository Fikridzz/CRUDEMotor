package co.id.fikridzakwan.example.crudemotor.UI.Activity.editprofile;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import co.id.fikridzakwan.example.crudemotor.Data.remote.ApiClient;
import co.id.fikridzakwan.example.crudemotor.Data.remote.ApiInterface;
import co.id.fikridzakwan.example.crudemotor.Model.login.LoginData;
import co.id.fikridzakwan.example.crudemotor.Model.login.LoginResponse;
import co.id.fikridzakwan.example.crudemotor.Utilts.Constants;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditProfilePresenter implements EditProfileConstract.Presenter {

    private final EditProfileConstract.View view;
    private SharedPreferences pref;
    private ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);

    public EditProfilePresenter(EditProfileConstract.View view) {
        this.view = view;
    }

    @Override
    public void getDataUser(Context context) {
        pref = context.getSharedPreferences(Constants.pref_name, 0);

        LoginData loginData = new LoginData();

        loginData.setIduser(pref.getString(Constants.KEY_USER_ID, ""));
        loginData.setNamauser(pref.getString(Constants.KEY_USER_NAMA, ""));
        loginData.setAlamat(pref.getString(Constants.KEY_USER_ALAMAT, ""));
        loginData.setNotelp(pref.getString(Constants.KEY_USER_NOTELP, ""));
        loginData.setJenkel(pref.getString(Constants.KEY_USER_JENKEL, ""));

        view.showDataUser(loginData);
    }

    @Override
    public void updateUser(Context context, LoginData loginData) {
        view.showProgress();

        Call<LoginResponse> call = apiInterface.updateUser(
                Integer.valueOf(loginData.getIduser()),
                loginData.getNamauser(),
                loginData.getAlamat(),
                loginData.getJenkel(),
                loginData.getNotelp());
        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                view.hideProgress();

                if (response.isSuccessful() && response.body() != null) {
                    if (response.body().getResult() == 1) {
                        pref = context.getSharedPreferences(Constants.pref_name,0);
                        SharedPreferences.Editor editor = pref.edit();
                        editor.putString(Constants.KEY_USER_NAMA, loginData.getNamauser());
                        editor.putString(Constants.KEY_USER_ALAMAT, loginData.getAlamat());
                        editor.putString(Constants.KEY_USER_NOTELP, loginData.getNotelp());
                        editor.putString(Constants.KEY_USER_JENKEL, loginData.getJenkel());
                        editor.apply();
                        view.showSuccessUpdateUser(response.body().getMessage());
                    } else {
                        view.showSuccessUpdateUser(response.body().getMessage());
                    }
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                view.hideProgress();
                view.showSuccessUpdateUser(t.getMessage());
            }
        });
    }
}
