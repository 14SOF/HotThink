package skhu.sof14.hotthink.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import skhu.sof14.hotthink.model.entity.Point;
import skhu.sof14.hotthink.model.entity.User;

import java.util.List;

public interface PointRepository extends JpaRepository<Point, Long> {


    List<Point> findAllByUser(User user);


    @Transactional
    @Query("select sum(p.amount) from Point p where p.user = ?1")
    Long amountSum(User user);




}
