package by.piupuupuu.twittergram.web;

import by.piupuupuu.twittergram.model.request.LoginRequest;
import by.piupuupuu.twittergram.model.response.LoginResponse;

public interface WebClient {

    LoginResponse login(LoginRequest request);
}
