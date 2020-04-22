package skhu.sof14.hotthink.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name="post")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="post_idx")
    private Long postIdx;

    @Column(name="post_title")
    private String title;

    @Column(name="post_content")
    private String content;

    @Column(name="post_hit")
    private int hit;

    @Column(name="post_like")
    private int like;

    @Column(name="post_dateTime")
    private LocalDateTime createDate;

    @Column(name="post_type")
    private String type;

    @Column(name="user_user_idx")
    private int userIdx;

}
