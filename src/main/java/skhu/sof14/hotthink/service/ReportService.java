package skhu.sof14.hotthink.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import skhu.sof14.hotthink.model.dto.report.CommentReportDto;
import skhu.sof14.hotthink.model.dto.report.PostReportDto;
import skhu.sof14.hotthink.model.entity.Report;
import skhu.sof14.hotthink.model.entity.User;
import skhu.sof14.hotthink.repository.OverlapRepository;
import skhu.sof14.hotthink.repository.ReportRepository;
import skhu.sof14.hotthink.repository.UserRepository;

import java.util.List;

@Service
public class ReportService {

    @Autowired
    ModelMapper mapper;

    @Autowired
    ReportRepository reportRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    OverlapRepository overlapRepository;


    public Report postReport(int id, Long postId) {

        User user = new User();
        user = userRepository.findUserById(id);
        int reportIdx = UserService.getIdFromAuth();
        PostReportDto dto = new PostReportDto(user, postId);
        dto.setType("게시판");
        dto.setReportId(reportIdx);
        return reportRepository.save(mapper.map(dto, Report.class));
    }

    public boolean checkRepostService(Long postId) {
        List<Report> entity = reportRepository.findReportByTypeId(postId); // 신고된 게시물
        int curUserIdx = UserService.getIdFromAuth(); // 현재 로그인된 IDX
        if (entity == null) {
            return true;
        }

        for (int i = 0; i < entity.size(); i++) {
            for (Report report : entity) {
                int reportId = entity.get(i).getReportId();
                if (curUserIdx == reportId) {
                    return false;
                }

            }
        }
        return true;
    }


    //userId : 신고당할 댓글의 작성자
    //commentId : 댓글IDX
    public Report commentReport(int userId, Long commentId) {

        User user = new User();
        user = userRepository.findUserById(userId);
        int reportIdx = UserService.getIdFromAuth();
        CommentReportDto dto = new CommentReportDto(user, commentId);
        dto.setReportId(reportIdx);
        dto.setType("댓글");
        return reportRepository.save(mapper.map(dto, Report.class));
    }

    /* checkCommentReportService
    ->신고 DB의 typeId는 신고된 게시믈,댓글의 IDX를 저장하기때문에 게시물과 댓글의 idx가 동일한 경우가 있을 수 있음.
    ->따라서 중복신고 처리를위해 type이 "댓글"인 신고DB를 모두 꺼내서 [신고된 댓글의ID와 신고자ID] 가  [신고된 댓글의ID와 현재로그인된ID] 와 동일하다면
    중복신고로 처리함.
     */
    public boolean checkCommentReportService(Long commentId) {
        List<Report> entity = reportRepository.findReportByType("댓글");
        if (entity == null) {
            return true;
        }
        int curUserIdx = UserService.getIdFromAuth();
        for (int i = 0; i < entity.size(); i++) {
            for (Report report : entity) {
                if (entity.get(i).getTypeId() == commentId && entity.get(i).getReportId() == curUserIdx) {
                    return false;
                }
            }
        }
        return true;
    }
}
