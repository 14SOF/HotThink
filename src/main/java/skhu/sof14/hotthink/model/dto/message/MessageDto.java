package skhu.sof14.hotthink.model.dto.message;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import skhu.sof14.hotthink.model.dto.user.UserPostDto;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class MessageDto {
    String content;
    LocalDateTime dateTime;
    UserPostDto receiver;
    UserPostDto sender;
}
