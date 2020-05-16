package by.piupuupuu.twittergram.service.async;

import android.os.AsyncTask;

import by.piupuupuu.twittergram.model.request.LoginRequest;
import by.piupuupuu.twittergram.model.request.SingUpRequest;
import by.piupuupuu.twittergram.model.response.LoginResponse;
import by.piupuupuu.twittergram.web.WebClientImpl;

public class AsyncSingUpRequest extends AsyncTask<SingUpRequest, Integer, LoginResponse> {

    @Override
    protected LoginResponse doInBackground(SingUpRequest... arg) {
        LoginRequest loginRequest = WebClientImpl.getInstance().singup(arg[0]);
        return WebClientImpl.getInstance().login(loginRequest);
    }

}