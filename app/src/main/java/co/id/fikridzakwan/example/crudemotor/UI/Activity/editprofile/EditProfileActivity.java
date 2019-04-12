package co.id.fikridzakwan.example.crudemotor.UI.Activity.editprofile;

import androidx.appcompat.app.AppCompatActivity;
import co.id.fikridzakwan.example.crudemotor.Model.login.LoginData;
import co.id.fikridzakwan.example.crudemotor.R;

import android.app.ProgressDialog;
import android.os.Bundle;

public class EditProfileActivity extends AppCompatActivity implements EditProfileConstract.View {

    private EditProfilePresenter editProfilePresenter = new EditProfilePresenter(this);
    private ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void showSuccessUpdateUser(String msg) {

    }

    @Override
    public void showDataUser(LoginData loginData) {

    }
}
