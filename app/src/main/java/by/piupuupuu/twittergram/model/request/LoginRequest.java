package by.piupuupuu.twittergram.model.request;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginRequest {

    String nickname;
    String password;

    LoginRequest() {
    }
}
