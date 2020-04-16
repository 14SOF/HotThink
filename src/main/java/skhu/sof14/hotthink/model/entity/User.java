package skhu.sof14.hotthink.model.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_idx")
    int id;

    @Column(name = "user_id")
    String userId;

    @Column(name = "user_pw")
    String userPassword;

    @Column(name="user_nick")
    String nick;

    @Column(name="user_name")
    String name;

    @Column(name="user_phone")
    String phone;

    @Column(name="user_status")
    boolean status;
}
