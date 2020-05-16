package by.piupuupuu.twittergram.activity.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import by.piupuupuu.twittergram.R;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginFragment extends Fragment {
    private View view;
    private EditText nickname, password;
    private Button loginButton;
    private FragmentManager manager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.login_layout, container, false);
        init();
        return view;
    }

    public LoginFragment() {
    }

    private void init(){
        manager = getActivity().getSupportFragmentManager();
        password = (EditText) view.findViewById(R.id.login_password);
        loginButton = (Button) view.findViewById(R.id.loginBtn);


    }

}
