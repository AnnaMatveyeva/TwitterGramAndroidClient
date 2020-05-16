package by.piupuupuu.twittergram;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import by.piupuupuu.twittergram.activity.fragment.LoginFragment;
import by.piupuupuu.twittergram.activity.fragment.SingUpFragment;
import by.piupuupuu.twittergram.cache.CacheService;
import by.piupuupuu.twittergram.model.User;
import lombok.Getter;

@Getter
public class MainActivity extends AppCompatActivity {

    public static final String NICKNAME_KEY = "nickname";
    private static FragmentManager fragmentManager;
    private CacheService cacheService = CacheService.getInstance();


    protected void replaceLoginFragment() {
        fragmentManager
                .beginTransaction()
                .replace(R.id.frameContainer, new LoginFragment(),
                        "LoginFragment")
                .commit();
    }

    public static void replaceSingupFragment() {
        fragmentManager
                .beginTransaction()
                .replace(R.id.frameContainer, new SingUpFragment(), "SingupFragment")
                .commit();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    public void init() {
        fragmentManager = getSupportFragmentManager();
        cacheService.setFilesDir(getFilesDir());
        Intent intent;
        User user = cacheService.getUserFromCache();
        String token = cacheService.getTokenFromCache();

        if (user != null || token != null) {
            System.out.println("this is tokennn" +
                    "" + " " + token);
            if (user != null) {
                System.out.println("this is userrr" + user.toString());
            }
//            intent = new Intent(this, MainWallActivity.class);
//            intent.putExtra(NICKNAME_KEY, user.toString() + " token " + token);
//            startActivity(intent);
        } else replaceLoginFragment();

        findViewById(R.id.close_activity).setOnClickListener(
                new View.OnClickListener() {

                    @Override
                    public void onClick(View arg0) {
                        finish();

                    }
                });
    }
}
