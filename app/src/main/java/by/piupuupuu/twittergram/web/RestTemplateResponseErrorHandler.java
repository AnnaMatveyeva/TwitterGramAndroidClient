package by.piupuupuu.twittergram.web;

import android.content.Context;
import android.content.res.Resources;
import android.widget.Toast;

import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.IOException;

import by.piupuupuu.twittergram.MainActivity;
import by.piupuupuu.twittergram.cache.CacheService;
import by.piupuupuu.twittergram.service.AuthenticationServiceImpl;

import static org.springframework.http.HttpStatus.Series.CLIENT_ERROR;
import static org.springframework.http.HttpStatus.Series.SERVER_ERROR;

public class RestTemplateResponseErrorHandler
        implements ResponseErrorHandler {

    Context context;

    public void setContext(Context context) {
        this.context = context;
    }

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
        } else if (httpResponse.getStatusCode()
                .series() == CLIENT_ERROR) {
            if (httpResponse.getStatusCode() == HttpStatus.FORBIDDEN) {
                Toast.makeText(context, "Invalid login or password", Toast.LENGTH_SHORT).show();
                MainActivity.replaceLoginFragment();
            }
            if (CacheService.getInstance().getUserFromCache() != null) {
                AuthenticationServiceImpl.getInstance()
                        .login(CacheService.getInstance().getUserFromCache().getNickname(),
                                CacheService.getInstance().getUserFromCache().getPassword());
            } else throw new RuntimeException("cannot take token");
            if (httpResponse.getStatusCode() == HttpStatus.NOT_FOUND) {
                throw new Resources.NotFoundException();
            }
        }
    }
}