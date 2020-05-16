package by.piupuupuu.twittergram.web;


import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import by.piupuupuu.twittergram.model.request.LoginRequest;
import by.piupuupuu.twittergram.model.request.SingUpRequest;
import by.piupuupuu.twittergram.model.response.LoginResponse;


public class WebClientImpl implements WebClient {

    private final String baseUrl = "http://10.0.2.2:8080/";
    private final RestTemplate restTemplate = new RestTemplate();

    private static WebClient instance;

    public static WebClient getInstance() {
        if (instance == null) {
            instance = new WebClientImpl();
        }
        return instance;
    }

    private WebClientImpl() {
        restTemplate.getMessageConverters().add(new MappingJacksonHttpMessageConverter());
//        List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();
//        messageConverters.add(new FormHttpMessageConverter());
//        messageConverters.add(new StringHttpMessageConverter());
//        messageConverters.add(new MappingJacksonHttpMessageConverter());
//        restTemplate.setMessageConverters(messageConverters);
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
}
