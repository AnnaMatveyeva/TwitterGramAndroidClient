package by.piupuupuu.twittergram.service.async;

import android.os.AsyncTask;

import by.piupuupuu.twittergram.model.Story;
import by.piupuupuu.twittergram.web.WebClientImpl;

public class AsyncSendLikeToStoryRequest extends AsyncTask<String, Integer, Story> {
    @Override
    protected Story doInBackground(String... strings) {
        return WebClientImpl.getInstance().sendLikeToStory(strings[0], strings[1]);
    }
}
