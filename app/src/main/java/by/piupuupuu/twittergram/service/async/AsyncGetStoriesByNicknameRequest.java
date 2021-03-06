package by.piupuupuu.twittergram.service.async;

import android.os.AsyncTask;

import java.util.List;

import by.piupuupuu.twittergram.model.Story;
import by.piupuupuu.twittergram.web.WebClientImpl;

public class AsyncGetStoriesByNicknameRequest extends AsyncTask<String, Integer, List<Story>> {

    @Override
    protected List<Story> doInBackground(String... arg) {
        return WebClientImpl.getInstance().findStoriesByNickname(arg[0], arg[1]);
    }

}