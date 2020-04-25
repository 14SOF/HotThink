package skhu.sof14.hotthink.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import skhu.sof14.hotthink.model.entity.Post;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {
    Post findPostById(Long id);

    List<Post> findAllByType(String type, Pageable pageable);
}
