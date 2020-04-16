package skhu.sof14.hotthink.model.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
/*@Table(name = "post")*/
public class Post extends TimeEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private int post_idx;
    private String post_title;
    private String post_content;
    private int post_hit;
    private int post_like;
    private LocalDateTime post_dateTime;
    private String post_type;
    private int user_user_idx;

    @Builder
    public Post(int post_idx, String post_title, String post_content, int post_hit, int post_like, LocalDateTime post_dateTime, String post_type, int user_user_idx) {
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
