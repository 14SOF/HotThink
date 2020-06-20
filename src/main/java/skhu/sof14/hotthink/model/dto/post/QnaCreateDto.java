package skhu.sof14.hotthink.model.dto.post;


import lombok.*;
import skhu.sof14.hotthink.model.dto.user.UserBase;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@AllArgsConstructor
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

    public QnaCreateDto(String title, String content, LocalDateTime createDate){
        this.title = title;
        this.content= content;
        this.createDate = LocalDateTime.now();
    }






}
