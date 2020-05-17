package by.piupuupuu.twittergram.model;

import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.ext.JodaDeserializers;
import org.joda.time.LocalDate;

import lombok.Data;

@Data
public class Story {

    Long storyId;
    String text;
    String username;
    @JsonDeserialize(using = JodaDeserializers.LocalDateDeserializer.class)
    LocalDate date;
    Boolean isLiked;

}
