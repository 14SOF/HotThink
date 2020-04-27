package skhu.sof14.hotthink.model.dto.post;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import skhu.sof14.hotthink.model.dto.comment.CommentReadDto;
import skhu.sof14.hotthink.model.dto.user.UserPostDto;

import java.time.LocalDateTime;
import java.util.List;

@Setter
@ToString
public final class PostReadDto extends PostBase{
    String title;
    String content;
    int hit;
    int like;
    LocalDateTime createDate;

    @Getter
    String type;
    UserPostDto user;

    @Getter
    List<CommentReadDto> commentList;
}
