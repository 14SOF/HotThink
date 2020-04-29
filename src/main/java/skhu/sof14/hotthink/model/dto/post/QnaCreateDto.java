package skhu.sof14.hotthink.model.dto.post;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import skhu.sof14.hotthink.model.dto.user.UserBase;
import skhu.sof14.hotthink.model.entity.User;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class QnaCreateDto extends PostBase {
    @NonNull
    @Setter
    String title;
    @NonNull
    @Setter
    String content;

    LocalDateTime createDate;

    @Setter
    String type;
    @Setter
    UserBase user;

}
