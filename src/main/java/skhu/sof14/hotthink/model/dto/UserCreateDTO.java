package skhu.sof14.hotthink.model.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Repository;

@ToString
public class UserCreateDTO {
    @Setter
    @Getter
    String userId;
    @Setter
    @Getter
    String userPassword;
    @Setter
    @Getter
    String nick;
    @Setter
    @Getter
    String name;
    @Setter
    @Getter
    String phone;
}
