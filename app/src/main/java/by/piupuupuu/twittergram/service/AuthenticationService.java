package by.piupuupuu.twittergram.service;

import by.piupuupuu.twittergram.model.response.LoginResponse;
import lombok.SneakyThrows;

public interface AuthenticationService {

    LoginResponse login(String nickname, String password);

    @SneakyThrows
    LoginResponse singup(String nickname, String password, String confirmPass, String email);
}
