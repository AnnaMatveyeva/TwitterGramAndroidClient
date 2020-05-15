package by.piupuupuu.twittergram;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import by.piupuupuu.twittergram.activity.TestActivity;
import by.piupuupuu.twittergram.model.response.LoginResponse;
import by.piupuupuu.twittergram.service.AuthenticationService;
import by.piupuupuu.twittergram.service.AuthenticationServiceImpl;
import lombok.SneakyThrows;

public class MainActivity extends AppCompatActivity {

    private AuthenticationService authService = new AuthenticationServiceImpl();
    private Button btnLogin;
    private EditText nicknameView;
    private EditText passwordView;
    public static final String NICKNAME_KEY = "nickname";

    public static Context mContext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this.getApplicationContext();
        init();
    }

    private void init() {
        btnLogin = findViewById(R.id.btn_login);
        nicknameView = findViewById(R.id.nickname);
        passwordView = findViewById(R.id.password);
        System.out.println("app started");
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @SneakyThrows
            @Override
            public void onClick(View v) {
                Editable nickname = nicknameView.getText();
                Editable password = passwordView.getText();
                LoginResponse loginResponse = new AsyncRequest()
                        .execute(nickname.toString(), password.toString())
                        .get();

                Intent intent = new Intent(v.getContext(), TestActivity.class);
                intent.putExtra(NICKNAME_KEY, loginResponse.getNickname());
                startActivity(intent);
                System.out.println("onBtnClick");
            }
        });
    }
    class AsyncRequest extends AsyncTask<String, Integer, LoginResponse> {

        @Override
        protected LoginResponse doInBackground(String... arg) {
            return authService.login(arg[0],arg[1]);
        }

    }

}
