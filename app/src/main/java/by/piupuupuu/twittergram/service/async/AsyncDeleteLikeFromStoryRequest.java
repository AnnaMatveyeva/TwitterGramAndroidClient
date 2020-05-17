package by.piupuupuu.twittergram.service.async;

import android.os.AsyncTask;

import by.piupuupuu.twittergram.model.Story;
import by.piupuupuu.twittergram.web.WebClientImpl;

public class AsyncDeleteLikeFromStoryRequest extends AsyncTask<String, Integer, Story> {
    @Override
    protected Story doInBackground(String... strings) {
        return WebClientImpl.getInstance().deleteLikeFromStory(strings[0], strings[1]);
    }
}
