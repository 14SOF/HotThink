package skhu.sof14.hotthink.model.dto.rate;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class RateCreateDto {
    float score;
    long user;
}
