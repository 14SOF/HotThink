package skhu.sof14.hotthink.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import skhu.sof14.hotthink.model.entity.Rate;
import skhu.sof14.hotthink.model.entity.User;

import java.util.List;

@Repository
public interface RateRepository extends JpaRepository<Rate, Integer> {

    List<Rate> findAllByUser(User user);

    @Query(value = "select ifnull(round(avg(rate_score), 1), 0.0) from rate where user_user_idx=?1", nativeQuery = true)
    float queryByUser(int id);
}
