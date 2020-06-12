package skhu.sof14.hotthink.model.dto.report;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import skhu.sof14.hotthink.model.entity.User;
import skhu.sof14.hotthink.repository.PostRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Setter
@Getter
public class PostReportDto extends ReportBase {

    @NonNull
    @Setter
    String type;

    @Setter
    User user;

    @Setter
    Long typeId;

    @Setter
    int reportId;

    LocalDateTime createDate;

    @JsonCreator
    public PostReportDto(@JsonProperty("id") User user, @JsonProperty("PostId") Long typeId) {
        this.user = user;
        this.typeId = typeId;
        createDate = LocalDateTime.now();
    }


}
