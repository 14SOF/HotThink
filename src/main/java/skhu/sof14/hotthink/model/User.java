package skhu.sof14.hotthink.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long userIdx;

    private String userId;
    private String userPw;
    private String userNick;
    private String userName;
    private String userPhone;
    private boolean userStatus;
}
