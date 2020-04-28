package skhu.sof14.hotthink.model.dto.post;

import lombok.Setter;
import lombok.ToString;
import skhu.sof14.hotthink.model.dto.user.UserPostDto;
import skhu.sof14.hotthink.model.entity.Comment;

import java.time.LocalDateTime;
import java.util.List;

@ToString
@Setter
public class PostListElementDto extends PostBase{
    String title;
    int like;
    int hit;
    LocalDateTime createDate;
    UserPostDto user;
    int commentList;

    public void setCommentList(List<Comment> commentList){
        this.commentList = commentList.size();
    }
}
