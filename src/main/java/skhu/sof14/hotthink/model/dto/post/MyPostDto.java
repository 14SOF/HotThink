package skhu.sof14.hotthink.model.dto.post;

import lombok.Setter;
import lombok.ToString;
import skhu.sof14.hotthink.model.entity.Comment;
import skhu.sof14.hotthink.model.entity.Like;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@ToString
@Setter
public class MyPostDto extends PostBase{
    String title;
    int hit;
    int likeList;
    int commentList;
    String type;
    LocalDate createDate;

    public void setType(String type){
        switch (type){
            case "프리":
                this.type = "프리띵크";
                break;
            case "핫":
                this.type = "핫띵크";
                break;
            case "리얼":
                this.type = "리얼띵크";
                break;
            case "QNA":
                this.type = "QNA";
                break;
            default:
                this.type = "자유게시판";
        }
    }
    public void setCreateDate(LocalDateTime createDate){
        this.createDate = createDate.toLocalDate();
    }
    public void setLikeList(List<Like> likeList) {
        this.likeList = likeList == null ? 0 : likeList.size();
    }
    public void setCommentList(List<Comment> commentList) {
        this.commentList = commentList == null ? 0 : commentList.size();
    }

}
