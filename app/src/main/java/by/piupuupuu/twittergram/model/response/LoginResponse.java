package by.piupuupuu.twittergram.model.response;

import lombok.Data;

@Data
public class LoginResponse {

    String nickname;
    String token;
    String email;

    public LoginResponse(){}

}
