package by.piupuupuu.twittergram.cache;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import by.piupuupuu.twittergram.model.User;
import lombok.Data;
import lombok.SneakyThrows;

@Data
public class CacheService {

    private final ObjectMapper mapper = new ObjectMapper();
    private File userInfoCache;
    private File tokenCache;
    private static CacheService instance;
    private File filesDir;

    public static CacheService getInstance() {
        if (instance == null) {
            instance = new CacheService();
        }
        return instance;
    }


    @SneakyThrows
    public File createUserInfoCache(User user) {
        if (userInfoCache != null) {
            try (BufferedWriter buff = new BufferedWriter(new FileWriter(userInfoCache))) {
                buff.write(mapper.writeValueAsString(user));
            }
            return userInfoCache;
        } else {
            userInfoCache = new File(filesDir.getPath().toString() + "/userFile.txt");
            if (userInfoCache.createNewFile()) {
                try (BufferedWriter buff = new BufferedWriter(new FileWriter(userInfoCache))) {
                    buff.write(mapper.writeValueAsString(user));
                }
            }
            return userInfoCache;
        }
    }

    @SneakyThrows
    public File createTokenCache(String token) {

        if (tokenCache != null) {
            try (BufferedWriter buff = new BufferedWriter(new FileWriter(tokenCache))) {
                buff.write(token);
            }
            return tokenCache;
        } else {
            tokenCache = new File(filesDir.getPath().toString() + "/tokenFile.txt");

            if (tokenCache.createNewFile()) {
                try (BufferedWriter buff = new BufferedWriter(new FileWriter(tokenCache))) {
                    buff.write(token);
                }
            }
            return tokenCache;
        }
    }

    @SneakyThrows
    public User getUserFromCache() {
        if (userInfoCache != null) {
            User user = mapper.readValue(userInfoCache, User.class);
            System.out.println(user.toString());
            return user;
        }
        return null;
    }

    @SneakyThrows
    public String getTokenFromCache() {
        if (tokenCache != null) {
            BufferedReader reader = new BufferedReader(new FileReader(tokenCache));

            String token = reader.readLine();
            System.out.println("getToken");
            return token;
        }
        return null;
    }


}
