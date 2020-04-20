package skhu.sof14.hotthink.model.dto;

import lombok.Data;

@Data
public class UserCreateDTO {
    String userId;
    String userPassword;
    String nick;
    String name;
    String phone;
}
