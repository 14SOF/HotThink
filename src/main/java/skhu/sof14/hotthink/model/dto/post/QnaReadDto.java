package skhu.sof14.hotthink.model.dto.post;

import lombok.*;
import skhu.sof14.hotthink.model.dto.user.UserPostDto;

import java.time.LocalDateTime;

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
    UserPostDto user;

}

