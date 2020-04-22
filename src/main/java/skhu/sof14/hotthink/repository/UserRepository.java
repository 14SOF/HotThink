package skhu.sof14.hotthink.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import skhu.sof14.hotthink.model.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findUserByUserId(String userId);
    User findUserByNick(String nick);
    
}