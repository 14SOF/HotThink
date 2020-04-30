package skhu.sof14.hotthink.model.dto.user;
import lombok.Setter;
import lombok.ToString;

@Setter
@ToString
public class UserPostDto extends UserBase{
    String nick;
    boolean writer;
}