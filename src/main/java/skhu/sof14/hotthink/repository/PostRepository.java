package skhu.sof14.hotthink.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import skhu.sof14.hotthink.model.entity.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {
}
