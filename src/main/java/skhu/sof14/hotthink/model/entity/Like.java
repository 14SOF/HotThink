package skhu.sof14.hotthink.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "`like`")
@NoArgsConstructor
@Setter
@Getter
public class Like {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "like_idx")
    Long id;

    @ManyToOne
    @JoinColumn(name = "user_user_idx")
    @NonNull
    @Getter
    public User user;

    @ManyToOne
    @JoinColumn(name = "post_post_idx")
    Post post;

    @ManyToOne
    @JoinColumn(name = "comment_comment_idx")
    Comment comment;
}
