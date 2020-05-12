package skhu.sof14.hotthink.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import skhu.sof14.hotthink.model.entity.Comment;
import skhu.sof14.hotthink.model.entity.Post;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {

    Comment findCommentById(Long id);

    //읽기
    List<Comment> findAllByPost(Post post);

    //삭제
    @Transactional
    void deleteById(Long id);
}
