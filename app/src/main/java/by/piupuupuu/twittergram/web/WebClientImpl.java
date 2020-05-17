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
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import by.piupuupuu.twittergram.model.Story;
import by.piupuupuu.twittergram.model.request.LoginRequest;
import by.piupuupuu.twittergram.model.request.SingUpRequest;
import by.piupuupuu.twittergram.model.response.LoginResponse;


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
        restTemplate.setErrorHandler(new RestTemplateResponseErrorHandler());
        restTemplate.setMessageConverters(messageConverters);
    }

    @Override
    public LoginResponse login(LoginRequest request) {
        System.out.println(request.getNickname() + " " + request.getPassword());
        return restTemplate.postForObject(baseUrl + "login",
                request,
                LoginResponse.class);
    }

    @Override
    public LoginRequest singup(SingUpRequest request) {
        return restTemplate.postForObject(baseUrl + "registration",
                request,
                LoginRequest.class);
    }

    @Override
    public List<Story> getAllStories(String token) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + token);
        HttpEntity<String> entity = new HttpEntity<String>(headers);
        System.out.println("token + " + token);
        ResponseEntity<Story[]> exchange = restTemplate.exchange(baseUrl + "api/story/for-client?sortBy=date", HttpMethod.GET, entity, Story[].class);
        return Arrays.asList(exchange.getBody());

    }

    @Override
    public Story sendLikeToStory(String storyId, String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + token);
        HttpEntity<String> entity = new HttpEntity<String>(headers);
        System.out.println("token + " + token);

        return restTemplate.exchange(baseUrl + "api/story/like/" + storyId, HttpMethod.POST, entity, Story.class).getBody();
    }

    @Override
    public Story deleteLikeFromStory(String storyId, String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + token);
        HttpEntity<String> entity = new HttpEntity<String>(headers);
        System.out.println("token + " + token);

        ResponseEntity<Story> body = restTemplate.exchange(baseUrl + "api/story/like/delete/" + storyId, HttpMethod.POST, entity, Story.class);
        return body.getBody();
    }
}
