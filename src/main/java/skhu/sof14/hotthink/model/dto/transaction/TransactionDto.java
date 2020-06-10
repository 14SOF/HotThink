package skhu.sof14.hotthink.model.dto.transaction;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import skhu.sof14.hotthink.model.dto.post.PostBase;

@ToString
@Getter
@Setter
public class TransactionDto {
    int price;
    PostBase post;
    boolean status;
}
