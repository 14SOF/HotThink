package skhu.sof14.hotthink.model.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name="post")
@ToString
@Setter
@Getter
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="post_idx")
    private Long id;

    @Column(name="post_title")
    private String title;

    @Column(name="post_content")
    private String content;

    @Column(name="post_hit")
    private int hit;

    @Column(name="post_date_time")
    private LocalDateTime createDate;

    @Column(name="post_type")
    private String type;

    @ManyToOne
    @JoinColumn(name = "user_user_idx")
    private User user;

    @OneToMany(mappedBy = "post")
    private List<Comment> commentList;

    @OneToMany(mappedBy = "post")
    private List<Like> likeList;

}
