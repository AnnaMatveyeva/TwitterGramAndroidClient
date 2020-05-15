package by.piupuupuu.twittergram.service;

import by.piupuupuu.twittergram.model.response.LoginResponse;

public interface AuthenticationService {

    LoginResponse login(String nickname, String password);
}
