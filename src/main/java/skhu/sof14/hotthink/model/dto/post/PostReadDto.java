package skhu.sof14.hotthink.model.dto.post;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import skhu.sof14.hotthink.model.dto.comment.CommentReadDto;
import skhu.sof14.hotthink.model.dto.user.UserPostDto;
import skhu.sof14.hotthink.model.entity.Like;

import java.time.LocalDateTime;
import java.util.List;

@Setter
@ToString
public class PostReadDto extends PostBase {
    String title;
    @Getter
    String content;
    int hit;
    @Getter
    LocalDateTime createDate;

    @Getter
    String type;
    @Getter
    UserPostDto user;

    @Getter
    int likeList;

    public void setLikeList(List<Like> likeList) {
        this.likeList = likeList == null ? 0 : likeList.size();
    }

    @Getter
    List<CommentReadDto> commentList;

    boolean userLikeStatus;

}
