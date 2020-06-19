package skhu.sof14.hotthink.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "rate")
@Data
public class Rate {
    @ManyToOne
    @JoinColumn(name = "user_user_idx")
    User user;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rate_idx")
    private int id;
    @Column(name = "rate_score")
    private float score;
    @Column(name = "rate_date_time")
    private LocalDateTime dateTime;
}
