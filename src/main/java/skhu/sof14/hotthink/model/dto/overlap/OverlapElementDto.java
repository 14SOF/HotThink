package skhu.sof14.hotthink.model.dto.overlap;

import lombok.Setter;
import lombok.ToString;
import skhu.sof14.hotthink.model.entity.User;

@ToString
@Setter
public class OverlapElementDto  extends OverlapBase{
    String title;
    String content;
    String evidence;
    String postId;

}
