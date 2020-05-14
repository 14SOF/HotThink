package skhu.sof14.hotthink.model.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
/*
user_user_idx : 게시글(신고된 게시글) 올린 사람의 IDX
report_report_idx : 신고한 사람의 IDX
report_type_idx : 신고된 게시물의 게시글 IDX
 */
import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="report")
@ToString
@Getter
@Setter
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="report_idx")
    private Long id;

    @Column(name="report_date_time")
    private LocalDateTime createDate;

    @Column(name="report_type")
    private String type;


    @Column(name="report_type_idx")
    private Long typeId;

    @Column(name="report_report_Id")
    private int reportId;

    @ManyToOne
    @JoinColumn(name="user_user_idx")
    private User user;


}
