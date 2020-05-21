package skhu.sof14.hotthink.model.dto.Point;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import skhu.sof14.hotthink.model.entity.User;

import java.time.LocalDateTime;

@Getter
@ToString
public class PointChargeDto extends PointBase {

    @Setter
    Long amount;

    LocalDateTime createDate;

    @Setter
    User user;

    @JsonCreator
    public PointChargeDto(@JsonProperty("money") Long amount) {
        this.amount = amount;
        this.createDate = LocalDateTime.now();
    }

}
