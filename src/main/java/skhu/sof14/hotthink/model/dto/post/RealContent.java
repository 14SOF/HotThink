package skhu.sof14.hotthink.model.dto.post;

import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
public class RealContent {
    String summary;
    String outline;
    String content;
    String effect;
    String similar;
    String difference;
    String patent;
    Long price;
}
