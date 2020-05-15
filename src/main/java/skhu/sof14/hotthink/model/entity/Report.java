package skhu.sof14.hotthink.model.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
/*
user_user_idx : 게시글(신고된 게시글) 올린 사람의 IDX
report_report_idx : 신고한 사람의 IDX
report_type_idx : 신고된 게시물의 게시글 IDX
----------------------
post(게시글) 신고시  - Report 컨트롤러 기준
-> user_user_idx : id
-> report_report_idx :  현재 로그인된 id  / reportId
-> report_type_idx : postId
----------------------
comment(댓글) 신고시 - Report 컨트롤러 기준
->user_user_idx : userId
-> report_report_idx : 현재 로그인된 id / reportId
->report_type_idx : commentId
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
