package by.piupuupuu.twittergram.service;

import by.piupuupuu.twittergram.model.request.LoginRequest;
import by.piupuupuu.twittergram.model.response.LoginResponse;
import by.piupuupuu.twittergram.web.WebClient;
import by.piupuupuu.twittergram.web.WebClientImpl;

public class AuthenticationServiceImpl implements AuthenticationService {

    private WebClient client = new WebClientImpl();
    @Override
    public LoginResponse login(String nickname, String password) {

        return client.login(new LoginRequest(nickname,password));
    }
}
