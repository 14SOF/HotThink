package skhu.sof14.hotthink.controller;

import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import skhu.sof14.hotthink.model.dto.report.PostReportDto;
import skhu.sof14.hotthink.service.ReportService;
import skhu.sof14.hotthink.service.UserService;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Controller
public class ReportController {

    @Autowired
    ReportService reportService;


    @PostMapping("/post/report")
    public @ResponseBody //여기서 id는 글을 올린사람의 IDX
    Map<String, Boolean> postReport(int id, Long postId) {
        Map<String, Boolean> json = new HashMap<>();
        System.out.println(id + "," + postId);

        boolean checkOfReport = reportService.checkRepostService(postId);

        if(checkOfReport) {
            reportService.postReport(id, postId);
        }

        json.put("check",checkOfReport);

        return json;



    }
}
//    @PostMapping("/post/report")
//    public @ResponseBody void
//    postReport(int id, Long postId) {
//        System.out.println(id + "," + postId);
//        reportService.postReport(id, postId);
//    }


