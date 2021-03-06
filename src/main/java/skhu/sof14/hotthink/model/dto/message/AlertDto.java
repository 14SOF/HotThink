package skhu.sof14.hotthink.model.dto.message;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class AlertDto {
    private Type type;
    private String content;
    private int id;
    private LocalDateTime dateTime;

    public enum Type{
        Comment,
        LikePost,
        LikeComment,
    }
}
