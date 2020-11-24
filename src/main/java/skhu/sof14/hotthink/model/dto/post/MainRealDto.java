package skhu.sof14.hotthink.model.dto.post;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Setter
@Getter
@ToString
public class MainRealDto extends PostBase {

    String title;
    String type;
    LocalDateTime createDate;
}
