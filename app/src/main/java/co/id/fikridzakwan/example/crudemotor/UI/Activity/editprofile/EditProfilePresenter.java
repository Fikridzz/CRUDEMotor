package co.id.fikridzakwan.example.crudemotor.UI.Activity.editprofile;

import android.content.Context;

import co.id.fikridzakwan.example.crudemotor.Model.login.LoginData;

public class EditProfilePresenter implements EditProfileConstract.Presenter {

    private final EditProfileConstract.View view;

    public EditProfilePresenter(EditProfileConstract.View view) {
        this.view = view;
    }

    @Override
    public void getDataUser(Context context) {

    }

    @Override
    public void updateUser(Context context, LoginData loginData) {

    }
}
