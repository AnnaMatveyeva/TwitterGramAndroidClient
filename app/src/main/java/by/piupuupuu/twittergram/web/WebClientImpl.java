package by.piupuupuu.twittergram.web;


import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

import by.piupuupuu.twittergram.model.request.LoginRequest;
import by.piupuupuu.twittergram.model.response.LoginResponse;


public class WebClientImpl implements WebClient {

    private final String baseUrl = "http://10.0.2.2:8080/";
    private final RestTemplate restTemplate = new RestTemplate();

    public WebClientImpl(){
        restTemplate.getMessageConverters().add(new MappingJacksonHttpMessageConverter());
        List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();
        messageConverters.add(new FormHttpMessageConverter());
        messageConverters.add(new StringHttpMessageConverter());
        messageConverters.add(new MappingJacksonHttpMessageConverter());
        restTemplate.setMessageConverters(messageConverters);
    }

    @Override
    public LoginResponse login(LoginRequest request) {
        return restTemplate.postForObject(baseUrl + "login",
                request,
                LoginResponse.class);
    }
}
