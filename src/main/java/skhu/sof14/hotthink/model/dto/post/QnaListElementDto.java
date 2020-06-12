package skhu.sof14.hotthink.model.dto.post;

import lombok.Setter;
import lombok.ToString;
import skhu.sof14.hotthink.model.dto.user.UserPostDto;
import skhu.sof14.hotthink.model.entity.Comment;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@ToString
@Setter
public class QnaListElementDto extends PostBase {

    String title;
    LocalDate createDate;
    UserPostDto user;
    int hit;
    int commentList;
    boolean check = true;


    public void setCreateDate(LocalDateTime createDate){
        this.createDate = createDate.toLocalDate();
    }
    public void setCommentList(List<Comment> commentList) {
        this.commentList = commentList.size();
        if(commentList.size() ==0) {
            check = false;
        }
    }


}
