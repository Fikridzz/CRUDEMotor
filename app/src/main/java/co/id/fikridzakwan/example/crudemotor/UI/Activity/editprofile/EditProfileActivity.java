package co.id.fikridzakwan.example.crudemotor.UI.Activity.editprofile;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.zip.Inflater;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import co.id.fikridzakwan.example.crudemotor.Model.login.LoginData;
import co.id.fikridzakwan.example.crudemotor.R;
import de.hdodenhof.circleimageview.CircleImageView;

public class EditProfileActivity extends AppCompatActivity implements EditProfileConstract.View {

    @BindView(R.id.img_user)
    CircleImageView imgUser;
    @BindView(R.id.txt_change_photo)
    TextView txtChangePhoto;
    @BindView(R.id.edt_nama_user)
    EditText edtNamaUser;
    @BindView(R.id.edt_alamat)
    EditText edtAlamat;
    @BindView(R.id.edt_notelp)
    EditText edtNotelp;
    @BindView(R.id.spin_gender)
    Spinner spinGender;

    private EditProfilePresenter editProfilePresenter = new EditProfilePresenter(this);
    private ProgressDialog progressDialog;

    private String idUser, nama, alamat, noTelp;
    private int gender;
    private Menu action;

    private String mGender;
    private static final int GENDER_MALE = 1;
    private static final int GENDER_FEMALE = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        ButterKnife.bind(this);

        setupSpinner();

        editProfilePresenter.getDataUser(this);
    }

    private void setupSpinner() {
        ArrayAdapter genderSpinnerAdapter = ArrayAdapter.createFromResource(this, R.array.array_gender_option, android.R.layout.simple_spinner_item);
        genderSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinGender.setAdapter(genderSpinnerAdapter);
        spinGender.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selection = (String) parent.getItemAtPosition(position);
                if (!TextUtils.isEmpty(selection)) {
                    if (selection.equals(getString(R.string.gender_male))) {
                        mGender = "L";
                    } else if (selection.equals(getString(R.string.gender_female))) {
                        mGender = "P";
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                mGender = "L";
            }
        });
    }

    @Override
    public void showProgress() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading");
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

    @Override
    public void hideProgress() {
        progressDialog.dismiss();
    }

    @Override
    public void showSuccessUpdateUser(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        editProfilePresenter.getDataUser(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_editor, menu);
        action = menu;
        action.findItem(R.id.menu_save).setVisible(false);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_edit:
                edtMode();
                action.findItem(R.id.menu_edit).setVisible(false);
                action.findItem(R.id.menu_save).setVisible(true);

                return true;

            case R.id.menu_save:
                if (!TextUtils.isEmpty(idUser)) {
                    if (TextUtils.isEmpty(edtNamaUser.getText().toString()) ||
                            TextUtils.isEmpty(edtAlamat.getText().toString()) ||
                            TextUtils.isEmpty(edtNotelp.getText().toString())) {
                        android.app.AlertDialog.Builder alertDialog = new android.app.AlertDialog.Builder(this);
                        alertDialog.setMessage("Please complete the field!");
                        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                        alertDialog.show();
                    } else {
                        LoginData loginData = new LoginData();
                        loginData.setIduser(idUser);
                        loginData.setNamauser(edtNamaUser.getText().toString());
                        loginData.setAlamat(edtAlamat.getText().toString());
                        loginData.setNotelp(edtNotelp.getText().toString());
                        loginData.setJenkel(mGender);

                        editProfilePresenter.updateUser(this, loginData);

                        readMode();
                        action.findItem(R.id.menu_edit).setVisible(true);
                        action.findItem(R.id.menu_save).setVisible(false);
                    }
                } else {
                    readMode();
                    action.findItem(R.id.menu_edit).setVisible(true);
                    action.findItem(R.id.menu_save).setVisible(false);
                }
                return true;
                default:
            return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void showDataUser(LoginData loginData) {
        readMode();

        idUser = loginData.getIduser();
        nama = loginData.getNamauser();
        alamat = loginData.getAlamat();
        noTelp = loginData.getNotelp();

        if (loginData.getJenkel().equals("L")) {
            gender = 1;
        } else {
            gender = 2;
        }

        if (!TextUtils.isEmpty(idUser)) {
            ((AppCompatActivity) this).getSupportActionBar().setTitle("Profil " + nama);

            edtNamaUser.setText(nama);
            edtAlamat.setText(alamat);
            edtNotelp.setText(noTelp);

            switch (gender) {
                case GENDER_MALE:
                    spinGender.setSelection(0);
                    break;
                case GENDER_FEMALE:
                    spinGender.setSelection(1);
                    break;
            }
        } else {
            ((AppCompatActivity) this).getSupportActionBar().setTitle("Profil");
        }


    }

    private void readMode() {
        edtNamaUser.setFocusableInTouchMode(false);
        edtNotelp.setFocusableInTouchMode(false);
        edtAlamat.setFocusableInTouchMode(false);
        edtNamaUser.setFocusable(false);
        edtAlamat.setFocusable(false);
        edtNotelp.setFocusable(false);

        spinGender.setEnabled(false);
    }

    private void edtMode() {
        edtNamaUser.setFocusableInTouchMode(true);
        edtAlamat.setFocusableInTouchMode(true);
        edtNotelp.setFocusableInTouchMode(true);

        spinGender.setEnabled(true);
    }
}
