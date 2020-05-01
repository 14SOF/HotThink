package skhu.sof14.hotthink.model.dto.post;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import skhu.sof14.hotthink.model.dto.user.UserPostDto;

import java.time.LocalDateTime;

@Setter
@ToString
public class QnaReadDto extends PostBase {
    String title;
    String content;
    int hit;
    LocalDateTime createDate;

    @Getter
    String type;
    UserPostDto user;

}

