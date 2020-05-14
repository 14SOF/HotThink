package skhu.sof14.hotthink.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import skhu.sof14.hotthink.model.dto.report.PostReportDto;
import skhu.sof14.hotthink.model.entity.Report;
import skhu.sof14.hotthink.model.entity.User;
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

    public Report postReport(int id, Long postId) {

        User user = new User();
        user = userRepository.findUserById(id);
        int reportIdx = UserService.getIdFromAuth();
        PostReportDto dto  = new PostReportDto(user,postId);
        dto.setType("게시판");
        dto.setReportId(reportIdx);
        return reportRepository.save(mapper.map(dto, Report.class));
    }
    public  boolean checkRepostService(Long postId){
        List<Report> entity = reportRepository.findReportByTypeId(postId); // 신고된 게시물
        int curUserIdx = UserService.getIdFromAuth(); // 현재 로그인된 IDX
        if(entity == null){
            return true;
        }

        for(int i=0; i<entity.size(); i++){
            for(Report report : entity){
                int reportId = entity.get(i).getReportId();
                if(curUserIdx == reportId) {
                    return false;
                }

            }
        }

        return true;
    }

//    public boolean checkReport(Long postId, int reportId){
//        Report entity = reportRepository.checkReport(postId,reportId);
//        if(entity == null) {
//            return true;  //신고가능
//
//        }else {
//            return false; //이미 신고한 게시글입니다 출력.
//        }
//    }
}
