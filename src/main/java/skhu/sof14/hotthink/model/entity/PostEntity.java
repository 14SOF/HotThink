package skhu.sof14.hotthink.model.entity;

import lombok.*;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "post")
public class PostEntity extends TimeEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long post_idx;

    @Column(length = 50, nullable = false)
    private String post_title;
    @Column(columnDefinition = "TEXT", nullable = false)
    private String post_content;
    @Column
    private int post_hit = 0;
    @Column
    private int post_like = 0;
    @Column
    private String post_type = "프리";
    @Column
    private int user_user_idx = 28;

    @Builder
    public PostEntity(Long post_idx, String post_title, String post_content, int post_hit, int post_like,String post_type, int user_user_idx) {
        this.post_idx = post_idx;
        this.post_title = post_title;
        this.post_content = post_content;
        this.post_hit = post_hit;
        this.post_like = post_like;
        this.post_type = post_type;
        this.user_user_idx = user_user_idx;
    }

}
