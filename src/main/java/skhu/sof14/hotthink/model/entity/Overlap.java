package skhu.sof14.hotthink.model.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="overlap")
@Getter
@Setter
@ToString
public class Overlap {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="overlap_idx")
    private Long id;

    @Column(name ="overlap_title")
    private String title;

    @Column(name ="overlap_content")
    private String content;

    @Column(name= "overlap_evidence")
    private String evidence;

    @Column(name="overlap_date_time")
    LocalDateTime createDate;

    @Column(name ="overlap_post")
    private Long postId;

    @ManyToOne
    @JoinColumn(name ="user_user_idx")
    private User user;
}
