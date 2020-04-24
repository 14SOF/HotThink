package skhu.sof14.hotthink.model.dto.post;

import lombok.Setter;
import lombok.ToString;
import skhu.sof14.hotthink.model.entity.User;

import java.time.LocalDateTime;

@Setter
@ToString
public final class PostReadDto extends PostBase{
    String title;
    String content;
    int hit;
    int like;
    LocalDateTime createDate;
    String type;
    User user;
}
