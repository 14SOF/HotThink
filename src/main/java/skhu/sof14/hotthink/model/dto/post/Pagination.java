package skhu.sof14.hotthink.model.dto.post;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
public class Pagination {
    @NonNull
    @Setter
    @Getter
    int page;

    @Setter
    @Getter
    int recordCount;
}
