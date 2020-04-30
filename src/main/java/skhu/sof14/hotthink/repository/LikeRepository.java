package skhu.sof14.hotthink.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import skhu.sof14.hotthink.model.entity.Like;
import skhu.sof14.hotthink.model.entity.Post;
import skhu.sof14.hotthink.model.entity.User;

import java.util.List;

@Repository
public interface LikeRepository extends JpaRepository<Like, Integer> {
    List<Like> findAllByPost(Post post);

    @Transactional
    void deleteLikeByUserAndPost(User user, Post post);
}
