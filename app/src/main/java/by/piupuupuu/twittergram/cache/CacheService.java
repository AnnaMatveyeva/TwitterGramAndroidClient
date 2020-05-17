package by.piupuupuu.twittergram.cache;

import android.content.Context;

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
    private Context context;

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
                buff.flush();
            }
            return userInfoCache;
        } else {
            try (BufferedWriter buff = new BufferedWriter(new FileWriter(userInfoCache))) {
                buff.write(mapper.writeValueAsString(user));
                buff.flush();
            }
            return userInfoCache;
        }
    }

    @SneakyThrows
    public void setDirs() {
        userInfoCache = new File(filesDir.getPath().toString() + "/userFile.txt");
        tokenCache = new File(filesDir.getPath().toString() + "/tokenFile.txt");
        userInfoCache.createNewFile();
        tokenCache.createNewFile();
    }
    @SneakyThrows
    public File createTokenCache(String token) {

        if (tokenCache != null) {
            try (BufferedWriter buff = new BufferedWriter(new FileWriter(tokenCache))) {
                buff.write(token);
                buff.flush();
            }
            return tokenCache;
        } else {

            try (BufferedWriter buff = new BufferedWriter(new FileWriter(tokenCache))) {
                buff.write(token);
                buff.flush();
            }
            return tokenCache;
        }
    }

    @SneakyThrows
    public User getUserFromCache() {
        if (userInfoCache != null) {
            User user = mapper.readValue(userInfoCache, User.class);
            return user;
        }
        return null;
    }

    @SneakyThrows
    public String getTokenFromCache() {

        if (tokenCache != null) {
            BufferedReader reader = new BufferedReader(new FileReader(tokenCache));
            String token = reader.readLine();
            reader.close();
            return token;
        } else {
            tokenCache = new File(filesDir.getPath().toString() + "/tokenFile.txt");
            if (tokenCache.createNewFile()) {
                return null;
            } else return getTokenFromCache();
        }

    }


}
