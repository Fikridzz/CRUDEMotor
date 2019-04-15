package co.id.fikridzakwan.example.crudemotor.UI.Fragment.profile;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import butterknife.BindView;
import butterknife.OnClick;
import co.id.fikridzakwan.example.crudemotor.Model.login.LoginData;
import co.id.fikridzakwan.example.crudemotor.Model.motor.MotorData;
import co.id.fikridzakwan.example.crudemotor.R;
import co.id.fikridzakwan.example.crudemotor.UI.Activity.editprofile.EditProfileActivity;
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

    private ProfilePresenter mProfilePresenter = new ProfilePresenter(this);
    private String nama;

    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void showMotorListByUser(List<MotorData> motorDataList) {

    }

    @OnClick(R.id.cd_edit_profile)
    public void onViewClicked() {
        startActivity(new Intent(getContext(), EditProfileActivity.class));
    }
}
