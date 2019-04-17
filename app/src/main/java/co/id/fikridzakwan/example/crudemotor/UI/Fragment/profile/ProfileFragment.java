package co.id.fikridzakwan.example.crudemotor.UI.Fragment.profile;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import co.id.fikridzakwan.example.crudemotor.Data.adapter.AdapterByUserMotor;
import co.id.fikridzakwan.example.crudemotor.Model.login.LoginData;
import co.id.fikridzakwan.example.crudemotor.Model.motor.MotorData;
import co.id.fikridzakwan.example.crudemotor.R;
import co.id.fikridzakwan.example.crudemotor.UI.Activity.editprofile.EditProfileActivity;
import co.id.fikridzakwan.example.crudemotor.Utilts.Constants;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment implements ProfileConstract.View {


    @BindView(R.id.img_user)
    CircleImageView imgUser;
    @BindView(R.id.txt_nama_user)
    TextView txtNamaUser;
    @BindView(R.id.txt_post)
    TextView txtPost;
    @BindView(R.id.txt_angka_post)
    TextView txtAngkaPost;
    @BindView(R.id.cd_edit_profile)
    CardView cdEditProfile;
    @BindView(R.id.txt_your_post)
    TextView txtYourPost;
    @BindView(R.id.rv_by_user)
    RecyclerView rvByUser;
    Unbinder unbinder;

    private ProfilePresenter mProfilePresenter = new ProfilePresenter(this);
    private String idUser, nama;

    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        unbinder = ButterKnife.bind(this, view);

        SharedPreferences pref =  getContext().getSharedPreferences(Constants.pref_name,0);

        idUser = pref.getString(Constants.KEY_USER_ID,"");

        mProfilePresenter.getMotorListByUser(idUser);

        mProfilePresenter.getDataUser(getContext());

        setHasOptionsMenu(true);

        return view;
    }

    @Override
    public void showMotorListByUser(List<MotorData> motorDataList) {
        rvByUser.setAdapter(new AdapterByUserMotor(getContext(), motorDataList));
        rvByUser.setLayoutManager(new GridLayoutManager(getContext(), 3));
        txtAngkaPost.setText(String.valueOf(motorDataList.size()));
    }

    @Override
    public void showFailurMessage(String msg) {
        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showDataUser(LoginData loginData) {
        txtNamaUser.setText(loginData.getUsername());
    }

    @OnClick(R.id.cd_edit_profile)
    public void onViewClicked() {
        startActivity(new Intent(getContext(), EditProfileActivity.class));
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.logout, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_logout:
                mProfilePresenter.logoutSesion(getContext());
                return true;
                default:
                    return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onResume() {
        mProfilePresenter.getMotorListByUser(idUser);
        super.onResume();
    }
}
