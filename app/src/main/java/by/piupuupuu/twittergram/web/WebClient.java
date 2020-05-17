package by.piupuupuu.twittergram.web;

import java.util.List;

import by.piupuupuu.twittergram.model.Story;
import by.piupuupuu.twittergram.model.request.LoginRequest;
import by.piupuupuu.twittergram.model.request.SingUpRequest;
import by.piupuupuu.twittergram.model.response.LoginResponse;

public interface WebClient {

    LoginResponse login(LoginRequest request);

    LoginRequest singup(SingUpRequest request);

    List<Story> getAllStories(String token);

    Story sendLikeToStory(String storyId, String token);

    Story deleteLikeFromStory(String storyId, String token);
}
