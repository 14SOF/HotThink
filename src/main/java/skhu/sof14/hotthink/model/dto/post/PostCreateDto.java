package skhu.sof14.hotthink.model.dto.post;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;
import skhu.sof14.hotthink.model.entity.User;

import java.time.LocalDateTime;

@Getter
@ToString
public class PostCreateDto extends PostBase {
    @NonNull
    @Setter
    String title;
    @NonNull
    @Setter
    String content;

    LocalDateTime createDate;
    @Setter
    String type;
    @Setter
    User user;

    @JsonCreator
    public PostCreateDto(@JsonProperty("title") String title, @JsonProperty("content") String content) {
        this.title = title;
        this.content = content;
        this.createDate = LocalDateTime.now();
    }
}
