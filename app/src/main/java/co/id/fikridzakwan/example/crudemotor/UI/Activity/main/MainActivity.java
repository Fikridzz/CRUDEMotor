package co.id.fikridzakwan.example.crudemotor.UI.Activity.main;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import butterknife.BindView;
import butterknife.ButterKnife;
import co.id.fikridzakwan.example.crudemotor.R;
import co.id.fikridzakwan.example.crudemotor.UI.Fragment.home.HomeFragment;
import co.id.fikridzakwan.example.crudemotor.UI.Fragment.profile.ProfileFragment;
import co.id.fikridzakwan.example.crudemotor.UI.Fragment.search.SearchFragment;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.fl_container)
    FrameLayout flContainer;
    @BindView(R.id.navigation)
    BottomNavigationView navigation;
    @BindView(R.id.container)
    RelativeLayout container;
    private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    HomeFragment homeFragment = new HomeFragment();
                    loadFragment(homeFragment);
                    return true;
                case R.id.navigation_search:
                    SearchFragment searchFragment = new SearchFragment();
                    loadFragment(searchFragment);
                    return true;
                case R.id.navigation_profile:
                    ProfileFragment profileFragment = new ProfileFragment();
                    loadFragment(profileFragment);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        HomeFragment homeFragment = new HomeFragment();
        loadFragment(homeFragment);
        setTitle("Home");
    }

    private void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fl_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
