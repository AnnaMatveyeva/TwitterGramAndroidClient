package by.piupuupuu.twittergram.service;

import by.piupuupuu.twittergram.model.request.LoginRequest;
import by.piupuupuu.twittergram.model.response.LoginResponse;
import by.piupuupuu.twittergram.service.async.AsyncLoginRequest;
import lombok.SneakyThrows;

public class AuthenticationServiceImpl implements AuthenticationService {

    private static AuthenticationService instance;

    public static AuthenticationService getInstance() {
        if (instance == null) {
            instance = new AuthenticationServiceImpl();
        }
        return instance;
    }

    @Override
    @SneakyThrows
    public LoginResponse login(String nickname, String password) {
        LoginRequest request = new LoginRequest(nickname, password);
        AsyncLoginRequest asyncLoginRequest = new AsyncLoginRequest();
        return asyncLoginRequest.execute(request).get();
    }


}
