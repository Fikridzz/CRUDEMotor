package co.id.fikridzakwan.example.crudemotor.UI.Activity.detailbyuser;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import co.id.fikridzakwan.example.crudemotor.Model.motor.MotorData;
import co.id.fikridzakwan.example.crudemotor.R;
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

    private DetailByUserPresenter detailByUserPresenter = new DetailByUserPresenter(this);
    private String idKategory, idMotor;
    private MotorData mMotorData;
    private String[] mIdKategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_by_user);
        ButterKnife.bind(this);
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void successDelete() {

    }

    @Override
    public void successUpdate() {

    }

    @Override
    public void showDetailMotor(MotorData motorData) {

    }

    @Override
    public void showFailurMessage(String msg) {

    }
}
