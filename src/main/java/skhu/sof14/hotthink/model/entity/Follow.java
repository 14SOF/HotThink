package skhu.sof14.hotthink.model.entity;

import lombok.Generated;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name ="follow")
@NoArgsConstructor
@Setter
@Getter
public class Follow {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "follow_idx")
    Long id;

    @ManyToOne
    @JoinColumn(name = "user_follower")
    @Getter
    User userFollower;

    @ManyToOne
    @JoinColumn(name = "user_following")
    @Getter
    User userFollowing;

}
