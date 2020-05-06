package skhu.sof14.hotthink.model.dto.post;

import lombok.Setter;
import lombok.ToString;
import skhu.sof14.hotthink.model.dto.user.UserPostDto;
import skhu.sof14.hotthink.model.entity.Comment;
import skhu.sof14.hotthink.model.entity.Like;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@ToString
@Setter
public class PostListElementDto extends RealListElementDto{
    int like;
    int hit;
    LocalDate createDate;
    UserPostDto user;
    int commentList;

    @Override
    public void setContent(String content){
        if(content.length()>=50) this.content = content.substring(0, 50);
        else this.content = content;
    }
    public void setCreateDate(LocalDateTime createDate){
        this.createDate = createDate.toLocalDate();
    }
    public void setLike(List<Like> like){
        if(like == null) this.like = 0;
        else this.like = like.size();
    }
    public void setCommentList(List<Comment> commentList){
        this.commentList = commentList.size();
    }
}
