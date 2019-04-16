package co.id.fikridzakwan.example.crudemotor.UI.Activity.main;

import android.content.Context;

import co.id.fikridzakwan.example.crudemotor.Utilts.SessionManager;

public class MainPresenter implements MainConstract.Presenter {
    @Override
    public void logoutSesion(Context context) {
        SessionManager sessionManager = new SessionManager(context);
        sessionManager.logout();
    }
}
