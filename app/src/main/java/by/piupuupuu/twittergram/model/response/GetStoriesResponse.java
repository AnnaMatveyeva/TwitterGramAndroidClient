package by.piupuupuu.twittergram.model.response;

import java.util.List;

import by.piupuupuu.twittergram.model.Story;
import lombok.Data;

@Data
public class GetStoriesResponse {

    List<Story> stories;

    public GetStoriesResponse() {
    }
}
