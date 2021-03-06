package skhu.sof14.hotthink.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import skhu.sof14.hotthink.model.entity.Follow;
import skhu.sof14.hotthink.model.entity.User;

import java.util.List;

@Repository
public interface FollowRepository extends JpaRepository<Follow, Long> {

    List<Follow> findAllByUserFollowerOrUserFollowing(User userFollow, User userFollowing);

    @Transactional
    @Modifying
    @Query("delete from Follow f where f.userFollower = ?1 and f.userFollowing =?2")
    void deleteFollowByUserFollowerAndUserFollwing(User follower, User following);

}
