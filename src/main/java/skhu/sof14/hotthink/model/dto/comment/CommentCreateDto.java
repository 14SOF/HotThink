package skhu.sof14.hotthink.model.dto.comment;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import skhu.sof14.hotthink.model.dto.post.PostBase;
import skhu.sof14.hotthink.model.dto.user.UserBase;

import java.time.LocalDateTime;

@Setter
@Getter
@ToString
public class CommentCreateDto {
    String content;
    LocalDateTime dateTime;
    PostBase post;
    UserBase user;
}
