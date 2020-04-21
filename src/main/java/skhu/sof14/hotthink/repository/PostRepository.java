package skhu.sof14.hotthink.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import skhu.sof14.hotthink.model.entity.PostEntity;

@Repository
public interface PostRepository extends JpaRepository<PostEntity, Long> {
    }

