package skhu.sof14.hotthink.model.dto.report;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.apache.tomcat.jni.Local;
import skhu.sof14.hotthink.model.entity.User;

import java.time.LocalDateTime;

@Setter
@Getter
public class CommentReportDto extends ReportBase {
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
    public CommentReportDto(@JsonProperty("userId") User user, @JsonProperty("commentId") Long typeId){
        this.user = user;
        this.typeId= typeId;
        createDate = LocalDateTime.now();
    }
}
