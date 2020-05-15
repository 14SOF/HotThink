package skhu.sof14.hotthink.model.dto.message;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class AlertDto {
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
