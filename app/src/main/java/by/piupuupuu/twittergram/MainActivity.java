package by.piupuupuu.twittergram;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import by.piupuupuu.twittergram.activity.fragment.LoginFragment;
import by.piupuupuu.twittergram.activity.fragment.SingUpFragment;
import by.piupuupuu.twittergram.service.AuthenticationService;
import by.piupuupuu.twittergram.service.AuthenticationServiceImpl;

public class MainActivity extends AppCompatActivity {

    private AuthenticationService authService = new AuthenticationServiceImpl();
    public static final String NICKNAME_KEY = "nickname";
    private static FragmentManager fragmentManager;
    public static Context mContext;


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
        fragmentManager = getSupportFragmentManager();

        replaceLoginFragment();
        findViewById(R.id.close_activity).setOnClickListener(
                new View.OnClickListener() {

                    @Override
                    public void onClick(View arg0) {
                        finish();

                    }
                });

//        mContext = this.getApplicationContext();
//        init();
    }


}
