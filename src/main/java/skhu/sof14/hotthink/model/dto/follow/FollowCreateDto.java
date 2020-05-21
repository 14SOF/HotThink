package skhu.sof14.hotthink.model.dto.follow;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import skhu.sof14.hotthink.model.entity.User;

@ToString
@Getter
@Setter
public class FollowCreateDto extends FollowBase {
    User userFollower;
    User userFollwing;
}
