package skhu.sof14.hotthink.model.dto.message;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class MessageDto {
    private Type type;
    private String content;
    private int id;


    @JsonIgnore
    private LocalDateTime dateTime;

    public enum Type{
        Message,
        Comment,
        LikePost,
        LikeComment,
    }
}
