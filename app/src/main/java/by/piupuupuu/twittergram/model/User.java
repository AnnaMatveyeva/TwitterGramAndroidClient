package by.piupuupuu.twittergram.model;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class User {

    String nickname;
    String password;
    String email;

    public User() {
    }
}
