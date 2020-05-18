package skhu.sof14.hotthink.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import skhu.sof14.hotthink.model.dto.overlap.OverlapDto;
import skhu.sof14.hotthink.repository.OverlapRepository;
import skhu.sof14.hotthink.service.OverlapService;

import java.util.HashMap;
import java.util.Map;

@Controller
public class OverlapController {
    //    표절신고는 중복신고(여러번 신고) 가능하도록 구현함.
    @Autowired
    OverlapService overlapService;


    @GetMapping("/report/overlap")
    public String overlapRepostPage(@RequestParam Long postId, Model model) {
        model.addAttribute("postId", postId);
        System.out.println(postId);
        return "overlap_report";
    }

    @PostMapping("/check/overlap/report")
    public @ResponseBody
    Map<String, Boolean> overlapCheck(Long postId) {
        Map<String, Boolean> json = new HashMap<>();
        boolean checkOfOverlapReport = overlapService.overlapCheckService(postId);

        json.put("check", checkOfOverlapReport);

        return json;
    }


    @PostMapping("/report/overlap")
    public @ResponseBody
    void overlapReport(String title, String content, String evidence, Long postId) {
        overlapService.overlapReport(new OverlapDto(title, content, evidence), postId);
        System.out.println(title + "," + content + "," + evidence + "," + postId);
    }
}
