package skhu.sof14.hotthink.model.dto.rate;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class RateReadDto {
    float score;
    LocalDateTime dateTime;
}
