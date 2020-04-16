package skhu.sof14.hotthink.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import skhu.sof14.hotthink.model.entity.Post;

public interface PostRepository extends JpaRepository<Post, Long> {
}
