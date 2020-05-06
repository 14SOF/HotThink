package skhu.sof14.hotthink.model.dto.post;

import lombok.Setter;
import lombok.ToString;
import skhu.sof14.hotthink.model.dto.user.UserPostDto;

import java.time.LocalDateTime;

@ToString
@Setter
public class QnaListElementDto extends PostBase {

    String title;
    LocalDateTime createDate;
    UserPostDto user;
    int hit;



}
