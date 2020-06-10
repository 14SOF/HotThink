package skhu.sof14.hotthink.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "transaction")
@Data
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transaction_idx")
    private int id;

    @Column(name = "transaction_date_time")
    private LocalDateTime dateTime;

    @Column(name = "transaction_price")
    private int price;

    @Column(name = "transaction_status")
    private boolean status;

    @OneToOne
    Post post;

    @ManyToOne
    @JoinColumn(name = "user_user_idx")
    User buyer;//구매자
}
