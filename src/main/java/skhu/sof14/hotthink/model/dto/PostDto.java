package skhu.sof14.hotthink.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import skhu.sof14.hotthink.model.entity.Post;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor

public class PostDto {
    private int post_idx;
    private String post_title;
    private String post_content;
    private int post_hit;
    private int post_like;
    private LocalDateTime post_dateTime;
    private String post_type;
    private int user_user_idx;

    public PostDto toEntity() {
        Post post = Post.builder()
                .post_idx(post_idx)
                .post_title(post_title)
                .post_content(post_content)
                .post_hit(post_hit)
                .post_like(post_like)
                .post_dateTime(post_dateTime)
                .post_type(post_type)
                .user_user_idx(user_user_idx)
                .build();
        return post;
    }

}
