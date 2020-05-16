package by.piupuupuu.twittergram.service.async;

import android.os.AsyncTask;

import by.piupuupuu.twittergram.model.request.LoginRequest;
import by.piupuupuu.twittergram.model.response.LoginResponse;
import by.piupuupuu.twittergram.web.WebClientImpl;

public class AsyncLoginRequest extends AsyncTask<LoginRequest, Integer, LoginResponse> {

    @Override
    protected LoginResponse doInBackground(LoginRequest... arg) {
        return WebClientImpl.getInstance().login(arg[0]);
    }

}