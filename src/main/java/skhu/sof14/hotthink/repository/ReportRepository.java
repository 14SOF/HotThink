package skhu.sof14.hotthink.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ResponseBody;
import skhu.sof14.hotthink.model.entity.Report;

import java.util.List;

@Repository
public interface ReportRepository extends JpaRepository<Report, Long> {

    List<Report> findReportByTypeId(Long id);

    List<Report> findReportByType(String type);

//    @Query("select r from Report r where r.postId=?1 and r.reportId=?2 ")
//    Report checkReport(Long postId, int reportId);

}

//reportId : 신고자  , postId : 신고된 게시물의 id
//이 두개로 조회해서 조회결과가 나온다면 중복신고임.
