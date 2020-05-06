package skhu.sof14.hotthink.model.dto.like;

import lombok.Getter;
import lombok.Setter;
import skhu.sof14.hotthink.model.dto.user.UserBase;

@Setter
@Getter
public class LikeUserDto extends LikeBase{
    public UserBase userBase;
}
