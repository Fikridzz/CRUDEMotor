package co.id.fikridzakwan.example.crudemotor.UI.Activity.detailbyuser;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import co.id.fikridzakwan.example.crudemotor.Model.login.LoginData;
import co.id.fikridzakwan.example.crudemotor.Model.motor.MotorData;
import co.id.fikridzakwan.example.crudemotor.R;
import co.id.fikridzakwan.example.crudemotor.UI.Activity.editbyuser.EditByUserActivity;
import co.id.fikridzakwan.example.crudemotor.Utilts.Constants;
import de.hdodenhof.circleimageview.CircleImageView;

public class DetailByUserActivity extends AppCompatActivity implements DetailByUserConstract.View {

    @BindView(R.id.img_user)
    CircleImageView imgUser;
    @BindView(R.id.txt_nama_user)
    TextView txtNamaUser;
    @BindView(R.id.txt_nama_motor)
    TextView txtNamaMotor;
    @BindView(R.id.img_motor)
    ImageView imgMotor;
    @BindView(R.id.txt_view)
    TextView txtView;
    @BindView(R.id.txt_view_number)
    TextView txtViewNumber;
    @BindView(R.id.txt_deskription)
    TextView txtDeskription;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;
    @BindView(R.id.overflow)
    ImageView overflow;

    private DetailByUserPresenter detailByUserPresenter = new DetailByUserPresenter(this);
    private String idKategory, idMotor, namaFotoMotor;
    private MotorData mMotorData;
    private LoginData loginData;
    private Uri filePath = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_by_user);
        ButterKnife.bind(this);

        idMotor = getIntent().getStringExtra(Constants.KEY_EXTRA_ID_MOTOR);
        detailByUserPresenter.getDetailMotor(idMotor);

        overflow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(DetailByUserActivity.this, v);
                popupMenu.inflate(R.menu.menu_edit_delete);
                popupMenu.show();

                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.menu_edit:
                                SharedPreferences pref = getSharedPreferences(Constants.pref_name, 0);
                                String mIdMotor = mMotorData.getIdMotor();
                                startActivity(new Intent(DetailByUserActivity.this, EditByUserActivity.class).putExtra(Constants.KEY_EXTRA_ID_MOTOR, mIdMotor));
                                break;
                            case R.id.menu_delete:
                                detailByUserPresenter.deleteDetailMotor(idMotor, namaFotoMotor);
                                break;
                        }
                        return false;
                    }
                });
            }
        });
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
    public void successDelete() {
        finish();
    }

    @Override
    public void showDetailMotor(MotorData motorData) {
        mMotorData = motorData;

        namaFotoMotor = motorData.getFotoMotor();

        RequestOptions options = new RequestOptions().error(R.drawable.ic_broken_image).placeholder(R.drawable.ic_broken_image);
        Glide.with(this).load(motorData.getUrlMotor()).apply(options).into(imgMotor);

        txtNamaMotor.setText(motorData.getNamaMotor());
        txtNamaUser.setText(motorData.getNamaUser());
        txtDeskription.setText(motorData.getDescMotor());
        txtViewNumber.setText(motorData.getView());
    }

    @Override
    public void showFailurMessage(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showMessage(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onResume() {
        detailByUserPresenter.getDetailMotor(idMotor);
        super.onResume();
    }
}
