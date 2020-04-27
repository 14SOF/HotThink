package skhu.sof14.hotthink.model.dto.comment;

import lombok.Setter;
import lombok.ToString;
import skhu.sof14.hotthink.model.dto.post.PostBase;
import skhu.sof14.hotthink.model.dto.user.UserPostDto;

@Setter
@ToString
public class CommentReadDto extends PostBase {
    String content;
    int like;
    UserPostDto user;
    boolean status;
}
