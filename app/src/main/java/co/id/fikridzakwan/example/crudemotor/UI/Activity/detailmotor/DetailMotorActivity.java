package co.id.fikridzakwan.example.crudemotor.UI.Activity.detailmotor;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import co.id.fikridzakwan.example.crudemotor.Model.motor.MotorData;
import co.id.fikridzakwan.example.crudemotor.R;
import co.id.fikridzakwan.example.crudemotor.Utilts.Constants;
import de.hdodenhof.circleimageview.CircleImageView;

public class DetailMotorActivity extends AppCompatActivity implements DetailMotorConstract.View {

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
    @BindView(R.id.pb_loading)
    ProgressBar pbLoading;
    @BindView(R.id.txt_info)
    TextView txtInfo;
    @BindView(R.id.rv_progress)
    RelativeLayout rvProgress;
    @BindView(R.id.sv_detail)
    ScrollView svDetail;

    private DetailMotorPresenter detailMotorPresenter = new DetailMotorPresenter(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_motor);
        ButterKnife.bind(this);

        getSupportActionBar().setTitle("Detail motor");
        String idMotor = getIntent().getStringExtra(Constants.KEY_EXTRA_ID_MOTOR);
        detailMotorPresenter.getDetailMotor(idMotor);
    }

    @Override
    public void showProgress() {
        rvProgress.setVisibility(View.VISIBLE);
        svDetail.setVisibility(View.GONE);
    }

    @Override
    public void hideProgress() {
        rvProgress.setVisibility(View.GONE);
        svDetail.setVisibility(View.VISIBLE);
    }

    @Override
    public void showDetailMotor(MotorData motorData) {
        RequestOptions options = new RequestOptions().error(R.drawable.ic_broken_image).placeholder(R.drawable.ic_broken_image);
        Glide.with(this).load(motorData.getUrlMotor()).apply(options).into(imgMotor);

        txtNamaUser.setText(motorData.getNamaUser());
        txtNamaMotor.setText(motorData.getNamaMotor());
        txtDeskription.setText(motorData.getDescMotor());
        txtViewNumber.setText(motorData.getView());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home: {
                finish();
                break;
            }
        }
        return true;
    }

    @Override
    public void showFailureMessage(String msg) {
        svDetail.setVisibility(View.GONE);
        rvProgress.setVisibility(View.VISIBLE);
        pbLoading.setVisibility(View.GONE);
        txtInfo.setText(msg);
    }
}
