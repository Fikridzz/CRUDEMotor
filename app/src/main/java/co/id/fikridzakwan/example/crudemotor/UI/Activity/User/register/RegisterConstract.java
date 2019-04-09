package co.id.fikridzakwan.example.crudemotor.UI.Activity.User.register;

import co.id.fikridzakwan.example.crudemotor.Model.login.LoginData;

public interface RegisterConstract {
    interface View {
        void showProgress();
        void hideProgress();
        void showError(String msg);
        void showRegisterSuccess(String msg);
    }
    interface Presenter {
        void doRegisterUser(LoginData loginData);
    }
}
