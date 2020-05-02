package skhu.sof14.hotthink.model.dto.user;

import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class UserCreateDto extends UserDetailDto{
    String userPassword;
    boolean status;
}
