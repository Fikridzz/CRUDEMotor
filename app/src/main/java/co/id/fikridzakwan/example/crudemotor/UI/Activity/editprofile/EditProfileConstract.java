package co.id.fikridzakwan.example.crudemotor.UI.Activity.editprofile;

import android.content.Context;

import co.id.fikridzakwan.example.crudemotor.Model.login.LoginData;

public interface EditProfileConstract {
    interface View {
        void showProgress();
        void hideProgress();
        void showSuccessUpdateUser(String msg);
        void showDataUser(LoginData loginData);
    }
    interface Presenter {
        void getDataUser(Context context);
        void updateUser(Context context, LoginData loginData);
    }
}
