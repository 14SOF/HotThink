package skhu.sof14.hotthink.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import skhu.sof14.hotthink.model.entity.Post;
import skhu.sof14.hotthink.model.entity.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
    Transaction findByPost(Post post);

    @Transactional
    @Modifying
    @Query("update Transaction t set t.status = true where t.post = ?1")
    void queryTransactionByPost(Post post);

    @Transactional
    void deleteTransactionByPost(Post post);

    @Transactional
    @Modifying
    @Query(
            value = "update transaction t " +
                    "inner join post p on t.post_post_idx = p.post_idx " +
                    "set transaction_rate = true " +
                    "where p.user_user_idx = ?1",
            nativeQuery = true
    )
    void queryTransactionByPost_User(int id);
}
