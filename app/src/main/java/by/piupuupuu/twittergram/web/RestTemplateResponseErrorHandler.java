package by.piupuupuu.twittergram.web;

import android.content.res.Resources;

import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.IOException;

import by.piupuupuu.twittergram.cache.CacheService;
import by.piupuupuu.twittergram.service.AuthenticationServiceImpl;

import static org.springframework.http.HttpStatus.Series.CLIENT_ERROR;
import static org.springframework.http.HttpStatus.Series.SERVER_ERROR;

public class RestTemplateResponseErrorHandler
        implements ResponseErrorHandler {

    @Override
    public boolean hasError(ClientHttpResponse httpResponse)
            throws IOException {

        return (
                httpResponse.getStatusCode().series() == CLIENT_ERROR
                        || httpResponse.getStatusCode().series() == SERVER_ERROR);
    }

    @Override
    public void handleError(ClientHttpResponse httpResponse)
            throws IOException {

        if (httpResponse.getStatusCode()
                .series() == SERVER_ERROR) {
            System.out.println("server error");
        } else if (httpResponse.getStatusCode()
                .series() == CLIENT_ERROR) {
            if (CacheService.getInstance().getUserFromCache() != null) {
                System.out.println("try to get new token");
                AuthenticationServiceImpl.getInstance()
                        .login(CacheService.getInstance().getUserFromCache().getNickname(),
                                CacheService.getInstance().getUserFromCache().getPassword());
                System.out.println("get new token");
            } else throw new RuntimeException("cannot take token");
            if (httpResponse.getStatusCode() == HttpStatus.NOT_FOUND) {
                throw new Resources.NotFoundException();
            }
        }
    }
}