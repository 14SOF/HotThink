package skhu.sof14.hotthink.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import skhu.sof14.hotthink.model.User;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
//    User findByUserId(String userId);
}