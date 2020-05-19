package by.piupuupuu.twittergram.activity.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import by.piupuupuu.twittergram.R;
import by.piupuupuu.twittergram.activity.MainWallActivity;
import by.piupuupuu.twittergram.model.response.LoginResponse;
import by.piupuupuu.twittergram.service.AuthenticationService;
import by.piupuupuu.twittergram.service.AuthenticationServiceImpl;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SingUpFragment extends Fragment {
    private View view;
    private EditText nickname;
    private EditText password;
    private EditText confirmPass;
    private EditText email;
    private Button singUpBtn;
    private FragmentManager manager;
    private AuthenticationService authenticationService = AuthenticationServiceImpl.getInstance();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.singup_layout, container, false);
        init();
        return view;
    }

    public SingUpFragment() {
    }

    private void init() {
        manager = getActivity().getSupportFragmentManager();
        password = (EditText) view.findViewById(R.id.singup_password);
        nickname = (EditText) view.findViewById(R.id.singup_nickname);
        confirmPass = (EditText) view.findViewById(R.id.singup_confirmPassword);
        email = (EditText) view.findViewById(R.id.singup_email);
        singUpBtn = (Button) view.findViewById(R.id.signUpBtn);

        singUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginResponse singup = authenticationService.singup(nickname.getText().toString(),
                        password.getText().toString(),
                        confirmPass.getText().toString(),
                        email.getText().toString());
                redirectToMainWall();
            }
        });

    }

    private void redirectToMainWall() {
        Intent intent = new Intent(getContext(), MainWallActivity.class);
        startActivity(intent);
        getActivity().finish();
    }
}
