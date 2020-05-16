package by.piupuupuu.twittergram.model.request;

import lombok.Value;

@Value
public class SingUpRequest {
    String nickname;
    String email;
    String password;
    String confirmPass;
}
