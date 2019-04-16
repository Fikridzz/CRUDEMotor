package co.id.fikridzakwan.example.crudemotor.UI.Fragment.upload;


import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import butterknife.BindView;
import butterknife.OnClick;
import co.id.fikridzakwan.example.crudemotor.Model.motor.MotorData;
import co.id.fikridzakwan.example.crudemotor.R;
import co.id.fikridzakwan.example.crudemotor.Utilts.Constants;

/**
 * A simple {@link Fragment} subclass.
 */
public class UploadFragment extends Fragment implements UploadConstract.View {

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
    @BindView(R.id.btn_upload)
    Button btnUpload;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;

    private UploadPresenter uploadPresenter = new UploadPresenter(this);

    private String idKategory;

    private Uri filePath;

    public UploadFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_upload, container, false);

        PermissionGalery();
        uploadPresenter.getKategory();
        return view;
    }

    private void PermissionGalery() {
        // Mengecek apakah user sudah memberikan permission untuk mengakses setelah storage
        if (ContextCompat.checkSelfPermission(getActivity(),
                Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)
            return;

        if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), Manifest.permission.READ_EXTERNAL_STORAGE)) {
            //If the user has denied the permission previously your code will come to this block
            //Here you can explain why you need this permission
            //Explain here why you need this permission
        }
        //And finally ask for the permission
        ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, Constants.STORAGE_PERMISSION_CODE);
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
    public void showMessage(String msg) {
        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void successUpload() {
        edtName.setText("");
        edtDesc.setText("");
        spinKategory.setAdapter(null);
        imgPicture.setImageResource(0);
    }

    @Override
    public void showSpinnerKategory(List<MotorData> kategoryDataList) {
        List<String> listSpinner = new ArrayList<>();
        for (int i = 0; i < kategoryDataList.size(); i++) {
            listSpinner.add(kategoryDataList.get(i).getNamaKategori());
        }

        ArrayAdapter<String> kategorySpinnerAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, listSpinner);
        kategorySpinnerAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinKategory.setAdapter(kategorySpinnerAdapter);

        spinKategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                idKategory = kategoryDataList.get(position).getIdKategori();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @OnClick({R.id.fab_choose_picture, R.id.btn_upload})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fab_choose_picture:
                showFileCussed();
                break;
            case R.id.btn_upload:
                uploadPresenter.uploadMotor(
                        getContext(),
                        filePath,
                        edtName.getText().toString(),
                        edtDesc.getText().toString(),
                        idKategory);
                break;
        }
    }

    private void showFileCussed() {
        // Membuka media ekternal storage
        Intent intentGalery = new Intent(Intent.ACTION_PICK);
        intentGalery.setType("image/*");
        intentGalery.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intentGalery, "Select picture"), Constants.REQUEST_CODE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // Mengecek apakah result coda dan data ada
        if (requestCode == Constants.REQUEST_CODE && resultCode == Activity.RESULT_OK && data != null && data.getData() != null) {
            // Mengambil data image yang sudah dipilih user
            filePath = data.getData();
            Log.i("Filepath", filePath.toString());

            try {
                // Mengubah file image pilihan user menjadi bitmap
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), filePath);
                // Menampilkan gambar priview yang di pilih
                imgPicture.setImageBitmap(bitmap);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
