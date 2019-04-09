package co.id.fikridzakwan.example.crudemotor.UI.Activity.User.login;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import co.id.fikridzakwan.example.crudemotor.Model.login.LoginData;
import co.id.fikridzakwan.example.crudemotor.R;
import co.id.fikridzakwan.example.crudemotor.UI.Activity.User.register.RegisterActivity;
import co.id.fikridzakwan.example.crudemotor.UI.Activity.main.MainActivity;
import co.id.fikridzakwan.example.crudemotor.Utilts.Constants;

public class LoginActivity extends AppCompatActivity implements LoginConstract.View {

    @BindView(R.id.edt_username)
    EditText edtUsername;
    @BindView(R.id.edt_password)
    EditText edtPassword;
    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.txt_create_account)
    TextView txtCreateAccount;

    private LoginPresenter loginPresenter = new LoginPresenter(this);
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        txtCreateAccount.setPaintFlags(txtCreateAccount.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

        loginPresenter.cekLogin(this);
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
    public void loginSuccess(String msg, LoginData loginData) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();

        LoginData mLoginData = new LoginData();

        mLoginData.setIduser(loginData.getIduser());
        mLoginData.setAlamat(loginData.getAlamat());
        mLoginData.setJenkel(loginData.getJenkel());
        mLoginData.setNamauser(loginData.getNamauser());
        mLoginData.setPassword(loginData.getPassword());
        mLoginData.setUsername(loginData.getUsername());
        mLoginData.setLevel(loginData.getLevel());
        mLoginData.setNotelp(loginData.getNotelp());

        startActivity(new Intent(this, MainActivity.class).putExtra(Constants.KEY_LOGIN, mLoginData));
        finish();
    }

    @Override
    public void loginFailure(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void usernameError(String msg) {
        edtUsername.setError(msg);
        edtUsername.setFocusable(true);
    }

    @Override
    public void passwordError(String msg) {
        edtPassword.setError(msg);
        edtPassword.setFocusable(true);
    }

    @Override
    public void isLogin() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    @OnClick({R.id.btn_login, R.id.txt_create_account})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                loginPresenter.doLogin(edtUsername.getText().toString(), edtPassword.getText().toString());
                break;
            case R.id.txt_create_account:
                startActivity(new Intent(this, RegisterActivity.class));
                break;
        }
    }
}
