package skhu.sof14.hotthink.model.dto.overlap;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class OverlapReadDto extends OverlapBase {
    String title;
    String content;
    String evidence;
    Long postId;
}
