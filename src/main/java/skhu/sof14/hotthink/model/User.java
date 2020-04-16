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
    public long user_idx;

    private String user_id;
    private String user_pw;
    private String user_nick;
    private String user_name;
    private String user_phone;
    private boolean user_status;
}
