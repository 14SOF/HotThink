package skhu.sof14.hotthink.model.dto.overlap;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import skhu.sof14.hotthink.model.entity.User;

import java.time.LocalDateTime;

@Getter
@ToString
public class OverlapDto extends OverlapBase {
    @Setter
    String title;

    @Setter
    String content;

    @Setter
    User user;

    @Setter
    String evidence; //증거링크

    @Setter
    Long postId; //신고박힌 게시글IDX

    LocalDateTime createDate;

    @JsonCreator
    public OverlapDto(@JsonProperty("title") String title, @JsonProperty("content") String content, @JsonProperty("evidence") String evidence){
        this.title = title;
        this.content= content;
        this.evidence = evidence;
        this.createDate= LocalDateTime.now();
    }

}
