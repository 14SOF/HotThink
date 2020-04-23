package skhu.sof14.hotthink.model.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Data
public class UserCreateDto extends UserDetailDto{
    String userPassword;
    boolean status;
}
