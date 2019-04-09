package co.id.fikridzakwan.example.crudemotor.Utilts;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import co.id.fikridzakwan.example.crudemotor.Model.login.LoginData;
import co.id.fikridzakwan.example.crudemotor.UI.Activity.User.login.LoginActivity;

public class SessionManager {

    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    private final Context context;

    public SessionManager(Context context) {
        this.context = context;
        pref = context.getSharedPreferences(Constants.pref_name, 0);
        editor = pref.edit();
    }

    public void createSesion(LoginData loginData) {
        editor.putBoolean(Constants.KEY_IS_LOGIN, true);
        editor.putString(Constants.KEY_USER_ID, loginData.getIduser());
        editor.putString(Constants.KEY_USER_NAMA, loginData.getNamauser());
        editor.putString(Constants.KEY_USER_ALAMAT, loginData.getAlamat());
        editor.putString(Constants.KEY_USER_NOTELP, loginData.getNotelp());
        editor.putString(Constants.KEY_USER_JENKEL, loginData.getJenkel());
        editor.putString(Constants.KEY_USER_LEVEL, loginData.getLevel());
        editor.putString(Constants.KEY_USER_USERNAME, loginData.getUsername());

        editor.commit();
    }

    public boolean isLogin() {
        return pref.getBoolean(Constants.KEY_IS_LOGIN, false);
    }

    public void logout() {
        editor.clear();
        editor.commit();

        Intent intent = new Intent(context, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(intent);
    }
}
