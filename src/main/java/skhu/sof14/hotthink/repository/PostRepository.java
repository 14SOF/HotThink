package skhu.sof14.hotthink.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import skhu.sof14.hotthink.model.entity.PostEntity;

public interface PostRepository extends JpaRepository<PostEntity, Long> {
    }

