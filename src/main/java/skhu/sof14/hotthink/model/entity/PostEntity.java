package skhu.sof14.hotthink.model.entity;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "post")
public class PostEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="post_idx")
    private Long postIdx;

    @Column(name = "post_title")
    private String postTitle;
    @Column(name = "post_content")
    private String postContent;
    @Column(name = "post_hit")
    private int postHit = 0;
    @Column(name = "post_like")
    private int postLike = 0;
    @Column(name = "post_date_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd kk:mm:ss")
    private LocalDateTime creatTime = LocalDateTime.now();
    @Column(name = "post_type")
    private String postType = "1";
    @Column(name = "user_user_idx")
    private int userIdx = 28; // 포링키라 우선 user 테이블에 있는 user_id값 임의로 넣었음


    @Builder
    public PostEntity(Long postIdx, String postTitle, String postContent, int postHit, int postLike, LocalDateTime creatTime, String postType, int userIdx) {
        this.postIdx = postIdx;
        this.postTitle = postTitle;
        this.postContent = postContent;
        this.postHit = postHit;
        this.postLike = postLike;
        this.creatTime = creatTime;
        this.postType = postType;
        this.userIdx = userIdx;
    }

}
