package skhu.sof14.hotthink.model.dto.post;

import lombok.Setter;
import lombok.ToString;
import skhu.sof14.hotthink.model.dto.user.UserPostDto;
import java.time.LocalDateTime;

@ToString
@Setter
public class PostListElementDto extends PostBase{
    String title;
    int like;
    int hit;
    LocalDateTime createDate;
    UserPostDto user;
}
