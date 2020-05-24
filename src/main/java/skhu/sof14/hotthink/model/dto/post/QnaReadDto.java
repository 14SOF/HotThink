package skhu.sof14.hotthink.model.dto.post;

import lombok.*;
import skhu.sof14.hotthink.model.dto.comment.CommentReadDto;
import skhu.sof14.hotthink.model.dto.user.UserPostDto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class QnaReadDto extends PostBase {
    String title;
    String content;
    int hit;
    LocalDateTime createDate;

    @Getter
    String type;
    @Getter
    UserPostDto user;

    @Getter
    List<CommentReadDto> commentList;



}

