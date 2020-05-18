package skhu.sof14.hotthink.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import skhu.sof14.hotthink.model.entity.Overlap;

import java.util.List;

@Repository
public interface OverlapRepository extends JpaRepository<Overlap, Long> {
    List<Overlap> findOverlapByPostId(Long postId);
}
