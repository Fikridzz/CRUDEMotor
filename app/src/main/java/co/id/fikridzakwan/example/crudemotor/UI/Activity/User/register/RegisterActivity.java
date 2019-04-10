package co.id.fikridzakwan.example.crudemotor.UI.Activity.User.register;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import co.id.fikridzakwan.example.crudemotor.Model.login.LoginData;
import co.id.fikridzakwan.example.crudemotor.R;
import co.id.fikridzakwan.example.crudemotor.UI.Activity.User.login.LoginActivity;

public class RegisterActivity extends AppCompatActivity implements RegisterConstract.View {

    @BindView(R.id.edt_nama_user)
    EditText edtNamaUser;
    @BindView(R.id.edt_alamat)
    EditText edtAlamat;
    @BindView(R.id.edt_notelp)
    EditText edtNotelp;
    @BindView(R.id.rb_male)
    RadioButton rbMale;
    @BindView(R.id.rb_female)
    RadioButton rbFemale;
    @BindView(R.id.edt_username)
    EditText edtUsername;
    @BindView(R.id.edt_password)
    EditText edtPassword;
    @BindView(R.id.rb_admin)
    RadioButton rbAdmin;
    @BindView(R.id.rb_user)
    RadioButton rbUser;
    @BindView(R.id.btn_register)
    Button btnRegister;

    private RegisterPresenter registerPresenter = new RegisterPresenter(this);
    private ProgressDialog progressDialog;
    private String jenkel;
    private String level;
    private String TAG = "TAG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);

        setRadio();
    }

    private void setRadio() {
        if (rbAdmin.isChecked()) {
            level = "1";
        } else {
            level = "0";
        }

        if (rbMale.isChecked()) {
            jenkel = "L";
        } else {
            jenkel = "P";
        }
    }

    @Override
    public void showProgress() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

    @Override
    public void hideProgress() {
        progressDialog.dismiss();
    }

    @Override
    public void showError(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showRegisterSuccess(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        startActivity(new Intent(this, LoginActivity.class));
        Log.i("cek" , "showRegisterSuccess: masuk");
        finish();
        return;
    }

    @OnClick({R.id.rb_male, R.id.rb_female, R.id.rb_admin, R.id.rb_user, R.id.btn_register})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rb_male:
                jenkel = "L";
                break;
            case R.id.rb_female:
                jenkel = "P";
                break;
            case R.id.rb_admin:
                level = "1";
                break;
            case R.id.rb_user:
                level = "0";
                break;
            case R.id.btn_register:
                LoginData mLoginData = new LoginData();

                mLoginData.setNamauser(edtNamaUser.getText().toString());
                mLoginData.setUsername(edtUsername.getText().toString());
                mLoginData.setPassword(edtPassword.getText().toString());
                mLoginData.setAlamat(edtAlamat.getText().toString());
                mLoginData.setNotelp(edtNotelp.getText().toString());
                mLoginData.setJenkel(jenkel);
                mLoginData.setLevel(level);

                registerPresenter.doRegisterUser(mLoginData);
                break;
        }
    }
}
