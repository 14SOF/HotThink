package skhu.sof14.hotthink.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import skhu.sof14.hotthink.model.entity.Rate;

@Repository
public interface RateRepository extends JpaRepository<Rate, Integer> {
}
