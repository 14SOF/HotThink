package skhu.sof14.hotthink.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import skhu.sof14.hotthink.model.dto.post.Pagination;
import skhu.sof14.hotthink.model.entity.Post;
import skhu.sof14.hotthink.model.entity.User;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {
    Post findPostById(Long id);

    Page<Post> findAllByTitleContainingAndType(String title, String type, Pageable pageable);

    Page<Post> findAllByType(String type, Pageable pageable);

    default List<Post> findAllByType(String type, Pagination pagination) {
        int size = 10;
        if(type.equals("핫")) size = 6;
        else if(type.equals("리얼")) size = 5;
        Pageable pageable = PageRequest.of(pagination.getPage() - 1, size);
        Page<Post> page;
        if(pagination.getTitle() == null) page = findAllByType(type, pageable);
        else page = findAllByTitleContainingAndType(pagination.getTitle(), type, pageable);
        pagination.setRecordCount((int) page.getTotalElements());
        return page.getContent();
    }

    Page<Post> findAllByUser(User user, Pageable pageable);

    default List<Post> findAllByUser(User user, Pagination pagination){
        Pageable pageable = PageRequest.of(pagination.getPage() - 1, 4);
        Page<Post> page = findAllByUser(user, pageable);
        pagination.setRecordCount((int) page.getTotalElements());
        return page.getContent();
    }

    @Transactional
    @Modifying
    @Query("update Post p set p.hit = p.hit+1 where p.id =?1")
    void updatePostByHit(Long id);


    @Modifying
    @Query("update Post p set p.title = ?2, p.content = ?3, p.createDate = ?4, p.type='리얼', p.hit=0 where p.id = ?1")
    void createReal(Long id, String title, String content, LocalDateTime time);

    @Transactional
    @Modifying
    @Query("update Post p set p.hit= 0, p.type='핫', p.hit=0 where p.user= ?1")
    void updatePostToHot(Long user);


}
