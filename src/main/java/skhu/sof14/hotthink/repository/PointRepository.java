package skhu.sof14.hotthink.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import skhu.sof14.hotthink.model.entity.Point;
import skhu.sof14.hotthink.model.entity.User;

public interface PointRepository extends JpaRepository<Point, Long> {



}
