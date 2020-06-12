package skhu.sof14.hotthink.model.dto.user;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@ToString
public class UserPostDto extends UserBase{
    @Getter
    String nick;
    boolean writer;
}
