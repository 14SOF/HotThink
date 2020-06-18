package skhu.sof14.hotthink.model.dto.transaction;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import skhu.sof14.hotthink.model.dto.post.PostBase;
import skhu.sof14.hotthink.model.dto.user.UserDetailDto;

@ToString
@Getter
@Setter
public class TransactionDto {
    int price;
    UserDetailDto buyer;
    PostBase post;
    boolean status;
}
