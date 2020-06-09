package skhu.sof14.hotthink.model.dto.post;

import lombok.*;
import org.springframework.web.bind.annotation.GetMapping;

@ToString
@RequiredArgsConstructor
public class Pagination {
    @NonNull
    @Setter
    @Getter
    int page;

    @Getter
    @Setter
    String title;


    @Setter
    @Getter
    int recordCount;
}