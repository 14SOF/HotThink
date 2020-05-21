package skhu.sof14.hotthink.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import skhu.sof14.hotthink.model.dto.post.Pagination;
import skhu.sof14.hotthink.model.entity.Overlap;

import java.util.List;

@Repository
public interface OverlapRepository extends JpaRepository<Overlap, Long> {
    List<Overlap> findOverlapByPostId(Long postId);

    Overlap findOverlapById(Long id);

    Page<Overlap> findAll(Pageable pageable);

    default List<Overlap> findAll(Pagination pagination) {
        int size = 10;
        Pageable pageable = PageRequest.of(pagination.getPage() - 1, size);
        Page<Overlap> page;
        page = findAll(pageable);
        pagination.setRecordCount((int) page.getTotalElements());
        return page.getContent();
    }

}
