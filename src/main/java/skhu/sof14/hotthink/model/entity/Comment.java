package skhu.sof14.hotthink.model.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Setter
@Getter
@Table(name = "comment")
@ToString(exclude = "post")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_idx")
    Long id;

    @Column(name = "comment_content")
    String content;

    @Column(name = "comment_date_time")
    LocalDateTime dateTime;

    @Column(name="comment_status")
    boolean status;

    @ManyToOne
    @JoinColumn(name = "post_post_idx")
    Post post;

    @ManyToOne
    @JoinColumn(name = "user_user_idx")
    User user;

    @OneToMany(mappedBy = "comment", fetch = FetchType.EAGER)
    private List<Like> likeList;
}
