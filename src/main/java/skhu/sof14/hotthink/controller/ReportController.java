package skhu.sof14.hotthink.controller;

import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import skhu.sof14.hotthink.model.dto.overlap.OverlapDto;
import skhu.sof14.hotthink.model.dto.report.PostReportDto;
import skhu.sof14.hotthink.model.entity.Overlap;
import skhu.sof14.hotthink.service.OverlapService;
import skhu.sof14.hotthink.service.ReportService;
import skhu.sof14.hotthink.service.UserService;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Controller
public class ReportController {

    @Autowired
    ReportService reportService;

    @PostMapping("/report/post")
    public @ResponseBody
        //여기서 id는 글을 올린사람의 IDX
    Map<String, Boolean> postReport(int id, Long postId) {
        Map<String, Boolean> json = new HashMap<>();
//        System.out.println(id + "," + postId);

        boolean checkOfReport = reportService.checkRepostService(postId);

        if (checkOfReport) {
            reportService.postReport(id, postId);
        }

        json.put("check", checkOfReport);

        return json;
    }

    @PostMapping("/check/comment/report")
    public @ResponseBody
    Map<String, Boolean> commentCheckReport(int userId, Long commentId) {
        Map<String, Boolean> json = new HashMap<>();
//        System.out.println(userId + "," + commentId);
        boolean checkOfCommentReport = reportService.checkCommentReportService(commentId);

        json.put("check", checkOfCommentReport);
        return json;

    }

    @GetMapping("/report/comment")
    public @ResponseBody void  commentReport(int userId, Long commentId) {
//        System.out.println(userId + "랑" + commentId + " 출력되면 신고등록되는거");
        reportService.commentReport(userId, commentId);
    }

//    @PostMapping("/report/overlap")
//    public @ResponseBody





}

