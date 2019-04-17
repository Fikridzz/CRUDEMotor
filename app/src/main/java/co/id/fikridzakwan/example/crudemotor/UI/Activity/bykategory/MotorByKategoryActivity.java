package co.id.fikridzakwan.example.crudemotor.UI.Activity.bykategory;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import co.id.fikridzakwan.example.crudemotor.Data.adapter.AdapterByKategoryMotor;
import co.id.fikridzakwan.example.crudemotor.Data.adapter.AdapterKategoryMotor;
import co.id.fikridzakwan.example.crudemotor.Model.motor.MotorData;
import co.id.fikridzakwan.example.crudemotor.R;
import co.id.fikridzakwan.example.crudemotor.Utilts.Constants;

public class MotorByKategoryActivity extends AppCompatActivity implements MotorByKategoryConstract.View {

    @BindView(R.id.pb_loading)
    ProgressBar pbLoading;
    @BindView(R.id.txt_info)
    TextView txtInfo;
    @BindView(R.id.rl_progress)
    RelativeLayout rlProgress;
    @BindView(R.id.rv_motor)
    RecyclerView rvMotor;
    @BindView(R.id.sr_motor)
    SwipeRefreshLayout srMotor;

    private final MotorByKategoryPresenter motorByKategoryPresenter = new MotorByKategoryPresenter(this);
    private String idKategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_motor_by_kategory);
        ButterKnife.bind(this);

        idKategory = getIntent().getStringExtra(Constants.KEY_EXTRA_ID_KATEGORY);

        motorByKategoryPresenter.getListMotorByKategory(idKategory);
        Log.i("cek", "idkateogry" + idKategory);

        srMotor.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                srMotor.setRefreshing(false);
                motorByKategoryPresenter.getListMotorByKategory(idKategory);
            }
        });
    }

    @Override
    public void showProgress() {
        rlProgress.setVisibility(View.VISIBLE);
        srMotor.setVisibility(View.GONE);
    }

    @Override
    public void hideProgress() {
        rlProgress.setVisibility(View.GONE);
        rvMotor.setVisibility(View.VISIBLE);
        srMotor.setVisibility(View.VISIBLE);
    }

    @Override
    public void showListMotorByKategory(List<MotorData> mototByKategoryList) {
        rvMotor.setLayoutManager(new LinearLayoutManager(this));
        rvMotor.setAdapter(new AdapterByKategoryMotor(this, mototByKategoryList));
    }

    @Override
    public void showFailureMessage(String msg) {
        srMotor.setVisibility(View.VISIBLE);
        rvMotor.setVisibility(View.GONE);
        pbLoading.setVisibility(View.GONE);
        txtInfo.setText(msg);
    }

    @Override
    public void onResume() {
        motorByKategoryPresenter.getListMotorByKategory(idKategory);
        super.onResume();
    }
}
