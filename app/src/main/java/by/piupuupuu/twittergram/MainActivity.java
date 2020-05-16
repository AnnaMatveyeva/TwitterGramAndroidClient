package by.piupuupuu.twittergram;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import by.piupuupuu.twittergram.activity.fragment.LoginFragment;
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
                       "LoginFragment").commit();
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

//    private void init() {
//        btnLogin = findViewById(R.id.btn_login);
//        nicknameView = findViewById(R.id.nickname);
//        passwordView = findViewById(R.id.password);
//        System.out.println("app started");
//        btnLogin.setOnClickListener(new View.OnClickListener() {
//            @SneakyThrows
//            @Override
//            public void onClick(View v) {
//                Editable nickname = nicknameView.getText();
//                Editable password = passwordView.getText();
//                LoginResponse loginResponse = new AsyncRequest()
//                        .execute(nickname.toString(), password.toString())
//                        .get();
//
//                Intent intent = new Intent(v.getContext(), TestActivity.class);
//                intent.putExtra(NICKNAME_KEY, loginResponse.getNickname());
//                startActivity(intent);
//                System.out.println("onBtnClick");
//            }
//        });
//    }
//    class AsyncRequest extends AsyncTask<String, Integer, LoginResponse> {
//
//        @Override
//        protected LoginResponse doInBackground(String... arg) {
//            return authService.login(arg[0],arg[1]);
//        }
//
//    }

}
