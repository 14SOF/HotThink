package skhu.sof14.hotthink.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import skhu.sof14.hotthink.model.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    User findUserById(int id);
    User findUserByUserId(String userId);
    User findUserByNick(String nick);


    @Modifying
    @Query("update User u set u.nick = ?2 where u.id = ?1")
    void updateNick(int id, String nick);

    @Modifying
    @Query("update User u set u.userPassword = ?2 where u.id = ?1")
    void updateUserPassword(int id, String pw);

    @Modifying
    @Query("update User u set u.nick = ?2, u.userPassword=?3 where u.id = ?1")
    void updateNickAndUserPassword(int id, String nick, String pw);

    @Transactional
    @Modifying
    @Query("update User u set u.status =0 where u.id=  ?1")
    void updateStatus(int id);
}
