package skhu.sof14.hotthink.model.dto;

import lombok.*;
import skhu.sof14.hotthink.model.entity.PostEntity;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class PostDto {
    private Long postIdx;
    private String postTitle;
    private String postContent;
    private int postHit = 0;
    private int postLike = 0;
    private LocalDateTime creatTime = LocalDateTime.now();
    private String postType = "1";
    private int userIdx = 28;


    public PostEntity toEntity() {
        PostEntity postEntity = PostEntity.builder()
                .postIdx(postIdx)
                .postTitle(postTitle)
                .postContent(postContent)
                .postHit(postHit)
                .postLike(postLike)
                .creatTime(creatTime)
                .postType(postType)
                .userIdx(userIdx)
                .build();
        return postEntity;
    }

    @Builder
    public PostDto(Long postIdx, String postTitle, String postContent, int postHit, int postLike, LocalDateTime creatTime, String postType, int userIdx) {
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
