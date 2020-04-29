package skhu.sof14.hotthink.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import skhu.sof14.hotthink.model.dto.post.Pagination;
import skhu.sof14.hotthink.model.entity.Post;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {
    Post findPostById(Long id);

    Page<Post> findAllByType(String type, Pageable pageable);

    default List<Post> findAllByType(String type, Pagination pagination) {
        Pageable pageable = PageRequest.of(pagination.getPage()-1, 10);
        Page<Post> page = findAllByType(type, pageable);
        pagination.setRecordCount((int) page.getTotalElements());
        return page.getContent();
    }

    @Modifying
    @Query("update Post p set p.title = ?2, p.content = ?3, p.createDate = ?4, p.type='리얼' where p.id = ?1")
    void createReal(Long id, String title, String content, LocalDateTime time);

}