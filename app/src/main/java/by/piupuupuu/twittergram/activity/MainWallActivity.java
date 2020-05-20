package by.piupuupuu.twittergram.activity;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import by.piupuupuu.twittergram.R;
import by.piupuupuu.twittergram.activity.fragment.SearchFragment;
import by.piupuupuu.twittergram.activity.fragment.UserProfileFragment;
import by.piupuupuu.twittergram.activity.fragment.WallFragment;

public class MainWallActivity extends AppCompatActivity {

    FragmentManager fragmentManager = getSupportFragmentManager();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_wall_activity);

        init();
        replaceWallFragment();
    }

    private void init() {
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.profile:
                        replaceUserProfileFragment();
                        break;
                    case R.id.addPost:
                        Toast.makeText(MainWallActivity.this, "Add Post", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.search:
                        replaceSearchFragment();
                        break;
                    case R.id.home:
                        replaceWallFragment();
                        break;
                }
                return true;
            }
        });
    }

    protected void replaceWallFragment() {
        fragmentManager
                .beginTransaction()
                .replace(R.id.frameContainer_wall_activity, new WallFragment(),
                        "WallFragment")
                .commit();
    }

    protected void replaceSearchFragment() {
        fragmentManager
                .beginTransaction()
                .replace(R.id.frameContainer_wall_activity, new SearchFragment(),
                        "SearchFragment")
                .commit();
    }
    protected void replaceUserProfileFragment() {
        fragmentManager
                .beginTransaction()
                .replace(R.id.frameContainer_wall_activity, new UserProfileFragment(),
                        "UserProfileFragment")
                .commit();
    }
}
