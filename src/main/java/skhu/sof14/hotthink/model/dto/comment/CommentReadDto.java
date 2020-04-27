package skhu.sof14.hotthink.model.dto.comment;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import skhu.sof14.hotthink.model.dto.user.UserPostDto;

@Setter
@ToString
public class CommentReadDto extends CommentBase {
    String content;
    int like;
    @Getter
    UserPostDto user;
    boolean status;
}
