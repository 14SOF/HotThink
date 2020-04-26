package skhu.sof14.hotthink.model.dto.post;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class PostUpdateDto{
    String title;
    RealContent content;
}
