package skhu.sof14.hotthink.model.dto;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserDetailDto extends UserBase {
    private String userId;
    private String nick;
    private String name;
    private String phone;
}
