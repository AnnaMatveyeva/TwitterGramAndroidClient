package by.piupuupuu.twittergram.service;

import java.util.List;

import by.piupuupuu.twittergram.cache.CacheService;
import by.piupuupuu.twittergram.model.Story;
import by.piupuupuu.twittergram.model.response.LoginResponse;
import by.piupuupuu.twittergram.service.async.AsyncDeleteLikeFromStoryRequest;
import by.piupuupuu.twittergram.service.async.AsyncGetAllStoryRequest;
import by.piupuupuu.twittergram.service.async.AsyncGetStoriesByOwner;
import by.piupuupuu.twittergram.service.async.AsyncGetStoriesByTextRequest;
import by.piupuupuu.twittergram.service.async.AsyncSendLikeToStoryRequest;
import lombok.SneakyThrows;

public class PostService {

    private static PostService instance;
    private AuthenticationService authenticationService = AuthenticationServiceImpl.getInstance();

    public static PostService getInstance() {
        if (instance == null) {
            instance = new PostService();
        }
        return instance;
    }

    @SneakyThrows
    public List<Story> findAll() {
        String tokenFromCache = CacheService.getInstance().getTokenFromCache();
        AsyncGetAllStoryRequest request = new AsyncGetAllStoryRequest();
        if (tokenFromCache != null) {
            List<Story> stories = request.execute(tokenFromCache).get();

            return stories;

        } else if (CacheService.getInstance().getUserFromCache() != null) {
            LoginResponse login = authenticationService.login(CacheService.getInstance().getUserFromCache().getNickname(),
                    CacheService.getInstance().getUserFromCache().getPassword());
            return request.execute(login.getToken()).get();
        } else return null;
    }


    @SneakyThrows
    public List<Story> findStoriesByText(String text) {
        String tokenFromCache = CacheService.getInstance().getTokenFromCache();
        AsyncGetStoriesByTextRequest request = new AsyncGetStoriesByTextRequest();
        if (tokenFromCache != null) {
            List<Story> stories = request.execute(text, tokenFromCache).get();
            return stories;

        } else if (CacheService.getInstance().getUserFromCache() != null) {
            LoginResponse login = authenticationService.login(CacheService.getInstance().getUserFromCache().getNickname(),
                    CacheService.getInstance().getUserFromCache().getPassword());
            return request.execute(text, login.getToken()).get();
        } else return null;
    }

    @SneakyThrows
    public Story sendLike(String storyId) {
        String tokenFromCache = CacheService.getInstance().getTokenFromCache();
        AsyncSendLikeToStoryRequest request = new AsyncSendLikeToStoryRequest();
        if (tokenFromCache != null) {

            return request.execute(storyId, tokenFromCache).get();
        } else if (CacheService.getInstance().getUserFromCache() != null) {
            LoginResponse login = authenticationService.login(CacheService.getInstance().getUserFromCache().getNickname(),
                    CacheService.getInstance().getUserFromCache().getPassword());
            return request.execute(storyId, login.getToken()).get();
        } else return null;
    }

    @SneakyThrows
    public Story deleteLike(String storyId) {
        String tokenFromCache = CacheService.getInstance().getTokenFromCache();
        AsyncDeleteLikeFromStoryRequest request = new AsyncDeleteLikeFromStoryRequest();
        if (tokenFromCache != null) {

            return request.execute(storyId, tokenFromCache).get();
        } else if (CacheService.getInstance().getUserFromCache() != null) {
            LoginResponse login = authenticationService.login(CacheService.getInstance().getUserFromCache().getNickname(),
                    CacheService.getInstance().getUserFromCache().getPassword());
            return request.execute(storyId, login.getToken()).get();
        } else return null;
    }

    @SneakyThrows
    public List<Story> getStoriesByUserId() {
        String tokenFromCache = CacheService.getInstance().getTokenFromCache();
        AsyncGetStoriesByOwner request = new AsyncGetStoriesByOwner();
        if (tokenFromCache != null) {
            List<Story> stories = request.execute(tokenFromCache).get();
            return stories;

        } else if (CacheService.getInstance().getUserFromCache() != null) {
            LoginResponse login = authenticationService.login(CacheService.getInstance().getUserFromCache().getNickname(),
                    CacheService.getInstance().getUserFromCache().getPassword());
            return request.execute(login.getToken()).get();
        } else return null;
    }

}
