package by.piupuupuu.twittergram.service;

import android.os.AsyncTask;

import by.piupuupuu.twittergram.cache.CacheService;
import by.piupuupuu.twittergram.model.User;
import by.piupuupuu.twittergram.model.request.LoginRequest;
import by.piupuupuu.twittergram.model.request.SingUpRequest;
import by.piupuupuu.twittergram.model.response.LoginResponse;
import by.piupuupuu.twittergram.service.async.AsyncLoginRequest;
import by.piupuupuu.twittergram.service.async.AsyncSingUpRequest;
import lombok.SneakyThrows;

public class AuthenticationServiceImpl implements AuthenticationService {

    private static AuthenticationService instance;
    private CacheService cacheService = CacheService.getInstance();

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

        LoginResponse loginResponse = asyncLoginRequest.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, request).get();
        cacheService.createTokenCache(loginResponse.getToken());
        cacheService.createUserInfoCache(new User(nickname, password, loginResponse.getEmail()));
        return loginResponse;
    }

    @Override
    @SneakyThrows
    public LoginResponse singup(String nickname, String password, String confirmPass, String email) {
        SingUpRequest singUpRequest = new SingUpRequest(nickname, email, password, confirmPass);
        AsyncSingUpRequest asyncSingUpRequest = new AsyncSingUpRequest();
        LoginResponse loginResponse = asyncSingUpRequest.execute(singUpRequest).get();
        cacheService.createTokenCache(loginResponse.getToken());
        cacheService.createUserInfoCache(new User(nickname, email, password));
        return loginResponse;
    }


}
