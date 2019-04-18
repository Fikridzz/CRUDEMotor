package co.id.fikridzakwan.example.crudemotor.UI.Activity.editbyuser;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.IOException;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import co.id.fikridzakwan.example.crudemotor.Model.motor.MotorData;
import co.id.fikridzakwan.example.crudemotor.R;
import co.id.fikridzakwan.example.crudemotor.Utilts.Constants;

public class EditByUserActivity extends AppCompatActivity implements EditByUserConstract.View {

    @BindView(R.id.img_picture)
    ImageView imgPicture;
    @BindView(R.id.fab_choose_picture)
    FloatingActionButton fabChoosePicture;
    @BindView(R.id.layoutPicture)
    CardView layoutPicture;
    @BindView(R.id.edt_name)
    EditText edtName;
    @BindView(R.id.edt_desc)
    EditText edtDesc;
    @BindView(R.id.spin_kategory)
    Spinner spinKategory;
    @BindView(R.id.layoutUploadMakanan)
    CardView layoutUploadMakanan;
    @BindView(R.id.btn_update)
    Button btnUpdate;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;

    private EditByUserPresenter mEditByUserPresenter = new EditByUserPresenter(this);
    private Uri filePath;
    private String idKategory, idMotor;
    private MotorData mMotorData;
    private String namaFotoMotor;
    private String[] mIdKategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_by_user);
        ButterKnife.bind(this);

        permissionGallery();
    }

    private void permissionGallery() {
        // Mencek apakah user sudah memberikan permission untuk mengakses external storage
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)
            return;

        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
            //If the user has denied the permission previously your code will come to this block
            //Here you can explain why you need this permission
            //Explain here why you need this permission
        }
        //And finally ask for the permission
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, Constants.STORAGE_PERMISSION_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == Constants.STORAGE_PERMISSION_CODE) {
            //If permission is granted
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //Displaying a toast
                showMessage("Permission granted now you can read the storage");
                Log.i("Permission on", "onRequestPermissionsResult: " + String.valueOf(grantResults));
            } else {
                //Displaying another toast if permission is not granted
                showMessage("Oops you just denied the permission");
                Log.i("Permission off", "onRequestPermissionsResult: " + String.valueOf(grantResults));
            }
        }
    }

    @Override
    public void showProgress() {
        swipeRefresh.setRefreshing(true);
    }

    @Override
    public void hideProgress() {
        swipeRefresh.setRefreshing(false);
    }

    @Override
    public void showDetailMotor(MotorData motorData) {
        mMotorData = motorData;
        idKategory = motorData.getIdKategori();
        namaFotoMotor = motorData.getFotoMotor();

        edtName.setText(motorData.getNamaMotor());
        edtDesc.setText(motorData.getDescMotor());

        for (int i = 0 ;i <mIdKategory.length; i++){

            Log.i("cek", "isi loop select mIdCategory: " + mIdKategory[i]);

            if (Integer.valueOf(mIdKategory[i]).equals(Integer.valueOf(idKategory))){
                spinKategory.setSelection(i);
                Log.i("cek", "isi select mIdCategory: " + mIdKategory[i]);
                Log.i("cek", "isi select idCategory: " + mIdKategory);
            }
        }

        RequestOptions options = new RequestOptions().error(R.drawable.ic_broken_image).placeholder(R.drawable.ic_broken_image);
        Glide.with(this).load(motorData.getUrlMotor()).apply(options).into(imgPicture);
    }

    @Override
    public void showMessage(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void successUpdate() {
        finish();
    }

    @Override
    public void showSpinnerKategory(List<MotorData> kategoryDataList) {
        String [] namaKategory = new String[kategoryDataList.size()];
        mIdKategory = new String[kategoryDataList.size()];

        for (int i = 0; i < kategoryDataList.size(); i++) {
            namaKategory[i] = kategoryDataList.get(i).getNamaKategori();
            mIdKategory[i] = kategoryDataList.get(i).getIdKategori();
        }

        ArrayAdapter<String> categorySpinnerAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, namaKategory);
        categorySpinnerAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinKategory.setAdapter(categorySpinnerAdapter);
        spinKategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Mengambil id category sesuai dengan pilihan user
                idKategory = kategoryDataList.get(position).getIdKategori();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        mEditByUserPresenter.getDetailMotor(idMotor);
    }

    @OnClick({R.id.fab_choose_picture, R.id.btn_update})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fab_choose_picture:
                permissionGallery();
                showFileChooser();
                break;
            case R.id.btn_update:
                mEditByUserPresenter.updateDataMotor(
                        this,
                        filePath,
                        edtName.getText().toString(),
                        edtDesc.getText().toString(),
                        idKategory,
                        namaFotoMotor,
                        idMotor);
                break;
        }
    }

    private void showFileChooser() {
        Intent intentGallery = new Intent(Intent.ACTION_PICK);
        intentGallery.setType("image/*");
        intentGallery.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intentGallery, "Select Pictures"), 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 &&
                resultCode == RESULT_OK &&
                data != null &&
                data.getData() != null
        ) {
            filePath = data.getData();

            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                imgPicture.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
