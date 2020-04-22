package skhu.sof14.hotthink.model.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "post")
public class PostEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long post_idx;

    @Column(name = "post_title")
    private String post_title;
    @Column(name = "post_content")
    private String post_content;
    @Column(name = "post_hit")
    private int post_hit = 0;
    @Column(name = "post_like")
    private int post_like = 0;
    @Column(name = "post_dateTime")
    private LocalDateTime post_dateTime = LocalDateTime.now();
    @Column(name = "post_type")
    private String post_type = "프리";
    @Column(name = "user_user_idx")
    private int user_user_idx = 28;

    @Builder
    public PostEntity(Long post_idx, String post_title, String post_content, int post_hit, int post_like, LocalDateTime post_dateTime,String post_type, int user_user_idx) {
        this.post_idx = post_idx;
        this.post_title = post_title;
        this.post_content = post_content;
        this.post_hit = post_hit;
        this.post_like = post_like;
        this.post_dateTime = post_dateTime;
        this.post_type = post_type;
        this.user_user_idx = user_user_idx;
    }

}
