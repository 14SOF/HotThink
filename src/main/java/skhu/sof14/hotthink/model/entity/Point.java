package skhu.sof14.hotthink.model.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name ="point")
@Getter
@Setter
@ToString
public class Point {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "point_idx")
    private Long id;

    @Column(name = "point_amount")
    private Long amount;

    @Column(name ="point_date_time")
    private LocalDateTime createDate;


    @OneToOne
    @JoinColumn(name ="user_user_idx")
    private User user;

}
