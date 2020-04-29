package skhu.sof14.hotthink.model.dto.user;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class UserUpdateDto {
    String nick;
    String userPassword;
}
