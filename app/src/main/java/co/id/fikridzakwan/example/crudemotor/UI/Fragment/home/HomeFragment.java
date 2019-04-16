package co.id.fikridzakwan.example.crudemotor.UI.Fragment.home;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import co.id.fikridzakwan.example.crudemotor.Data.adapter.AdapterKategoryMotor;
import co.id.fikridzakwan.example.crudemotor.Data.adapter.AdapterNewsMotor;
import co.id.fikridzakwan.example.crudemotor.Model.motor.MotorData;
import co.id.fikridzakwan.example.crudemotor.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment implements HomeConstract.View {

    @BindView(R.id.rv_motor_news)
    RecyclerView rvMotorNews;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;
    Unbinder unbinder;
    @BindView(R.id.rv_kategory)
    RecyclerView rvKategory;

    private HomePresenter mHomePresenter = new HomePresenter(this);

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        unbinder = ButterKnife.bind(this, view);

        mHomePresenter.getListKategoryMotor();
        mHomePresenter.getListNewsmotor();

        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mHomePresenter.getListNewsmotor();
                mHomePresenter.getListKategoryMotor();
            }
        });
        return view;
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
    public void showMotorListKategory(List<MotorData> motorKategoryList) {
        rvKategory.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        rvKategory.setAdapter(new AdapterKategoryMotor(getContext(), motorKategoryList));
    }

    @Override
    public void showMotorListPoplser(List<MotorData> motorPopulerList) {

    }

    @Override
    public void showMotorListNews(List<MotorData> motorNewsList) {
        rvMotorNews.setLayoutManager(new LinearLayoutManager(getContext()));
        rvMotorNews.setAdapter(new AdapterNewsMotor(getContext(), motorNewsList));
    }

    @Override
    public void showFailurMessage(String msg) {
        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onResume() {
        mHomePresenter.getListKategoryMotor();
        mHomePresenter.getListNewsmotor();
        super.onResume();
    }
}
