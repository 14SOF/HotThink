package skhu.sof14.hotthink.model.dto;

import lombok.*;
import skhu.sof14.hotthink.model.entity.PostEntity;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class PostDto {
    private Long post_idx;
    private String post_title;
    private String post_content;
    private int post_hit = 0;
    private int post_like = 0;
    private LocalDateTime post_dateTime;
    private String post_type = "프리";
    private int user_user_idx = 0;

    public PostEntity toEntity() {
        PostEntity postEntity = PostEntity.builder()
                .post_idx(post_idx)
                .post_title(post_title)
                .post_content(post_content)
                .post_hit(post_hit)
                .post_like(post_like)
                .post_dateTime(post_dateTime)
                .post_type(post_type)
                .user_user_idx(user_user_idx)
                .build();
        return postEntity;
    }

    @Builder
    public PostDto(Long post_idx, String post_title, String post_content, int post_hit, int post_like, LocalDateTime post_dateTime, String post_type, int user_user_idx) {
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
