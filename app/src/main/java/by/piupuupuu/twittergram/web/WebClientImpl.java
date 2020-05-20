package by.piupuupuu.twittergram.web;


import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import by.piupuupuu.twittergram.cache.CacheService;
import by.piupuupuu.twittergram.model.Story;
import by.piupuupuu.twittergram.model.request.LoginRequest;
import by.piupuupuu.twittergram.model.request.SingUpRequest;
import by.piupuupuu.twittergram.model.response.LoginResponse;
import by.piupuupuu.twittergram.service.AuthenticationServiceImpl;


public class WebClientImpl implements WebClient {

    private final String baseUrl = "http://10.0.2.2:8080/";
    private final RestTemplate restTemplate = new RestTemplate();
    private ObjectMapper mapper = new ObjectMapper();
    private static WebClient instance;

    public static WebClient getInstance() {
        if (instance == null) {
            instance = new WebClientImpl();
        }
        return instance;
    }

    private WebClientImpl() {
        restTemplate.getMessageConverters().add(new MappingJacksonHttpMessageConverter());
        List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();
        messageConverters.add(new FormHttpMessageConverter());
        messageConverters.add(new StringHttpMessageConverter());
        messageConverters.add(new MappingJacksonHttpMessageConverter());
        restTemplate.setMessageConverters(messageConverters);
    }

    @Override
    public LoginResponse login(LoginRequest request) {
        System.out.println(request.getNickname() + " " + request.getPassword());
        ResponseEntity<LoginResponse> exchangeResponse;
        try {
            exchangeResponse = restTemplate.exchange(baseUrl + "login",
                    HttpMethod.POST,
                    getHeadersAndBody(request),
                    LoginResponse.class);
            return exchangeResponse.getBody();
        } catch (HttpClientErrorException ex) {
            return null;
        }

    }

    @Override
    public LoginRequest singup(SingUpRequest request) {
        return restTemplate.postForObject(baseUrl + "registration",
                request,
                LoginRequest.class);
    }

    @Override
    public List<Story> getAllStories(String token) {
        System.out.println("token " + token);
        List<Story> stories;

        try {
            stories = Arrays.asList(restTemplate.exchange(baseUrl + "api/story/for-client?sortBy=date",
                    HttpMethod.GET,
                    getHeader(token),
                    Story[].class)
                    .getBody());
        } catch (RestClientException ex) {

            AuthenticationServiceImpl.getInstance().login(CacheService.getInstance().getUserFromCache().getNickname(),
                    CacheService.getInstance().getUserFromCache().getNickname());
            stories = getAllStories(CacheService.getInstance().getTokenFromCache());
        }
        return stories;
    }

    @Override
    public Story sendLikeToStory(String storyId, String token) {
        Story story;
        try {
            story = restTemplate.exchange(baseUrl + "api/story/like/" + storyId, HttpMethod.POST, getHeader(token), Story.class).getBody();
        } catch (RestClientException ex) {

            AuthenticationServiceImpl.getInstance().login(CacheService.getInstance().getUserFromCache().getNickname(),
                    CacheService.getInstance().getUserFromCache().getNickname());
            story = sendLikeToStory(storyId, CacheService.getInstance().getTokenFromCache());
        }
        return story;
    }

    @Override
    public Story deleteLikeFromStory(String storyId, String token) {
        Story story;
        try {
            story = restTemplate.exchange(baseUrl + "api/story/like/delete/" + storyId, HttpMethod.POST, getHeader(token), Story.class).getBody();
        } catch (RestClientException ex) {

            AuthenticationServiceImpl.getInstance().login(CacheService.getInstance().getUserFromCache().getNickname(),
                    CacheService.getInstance().getUserFromCache().getNickname());
            story = deleteLikeFromStory(storyId, CacheService.getInstance().getTokenFromCache());
        }
        return story;
    }

    @Override
    public List<Story> getByUserId(String token) {
        List<Story> stories;
        try {
            stories = Arrays.asList(restTemplate.exchange(baseUrl + "api/story/for-client-get-by-owner?sortBy=date&userId=",
                    HttpMethod.GET,
                    getHeader(token),
                    Story[].class).getBody());
        } catch (RestClientException ex) {

            System.out.println("create new login");
            AuthenticationServiceImpl.getInstance().login(CacheService.getInstance().getUserFromCache().getNickname(),
                    CacheService.getInstance().getUserFromCache().getNickname());
            stories = getByUserId(CacheService.getInstance().getTokenFromCache());
        }
        return stories;
    }

    private HttpEntity<String> getHeader(String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + token);
        headers.set("Connection", "close");
        return new HttpEntity<String>(headers);
    }

    private HttpEntity<LoginRequest> getHeadersAndBody(LoginRequest request) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Connection", "close");
        return new HttpEntity<LoginRequest>(request, headers);
    }
}
