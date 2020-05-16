package by.piupuupuu.twittergram.activity.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import by.piupuupuu.twittergram.MainActivity;
import by.piupuupuu.twittergram.R;
import by.piupuupuu.twittergram.model.response.LoginResponse;
import by.piupuupuu.twittergram.service.AuthenticationService;
import by.piupuupuu.twittergram.service.AuthenticationServiceImpl;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginFragment extends Fragment {
    private View view;
    private EditText nickname;
    private EditText password;
    private Button loginButton;
    private FragmentManager manager;
    private Button createButton;
    private AuthenticationService authenticationService = AuthenticationServiceImpl.getInstance();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.login_layout, container, false);
        init();
        return view;
    }

    public LoginFragment() {
    }

    private void init() {
        manager = getActivity().getSupportFragmentManager();
        password = (EditText) view.findViewById(R.id.login_password);
        loginButton = (Button) view.findViewById(R.id.loginBtn);
        nickname = (EditText) view.findViewById(R.id.login_nickname);
        createButton = view.findViewById(R.id.createAccount);

        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.replaceSingupFragment();
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginResponse login = authenticationService.login(nickname.getText().toString(), password.getText().toString());
                System.out.println(login.getToken());
            }
        });

    }

}
