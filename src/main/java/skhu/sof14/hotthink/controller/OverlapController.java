package skhu.sof14.hotthink.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import skhu.sof14.hotthink.model.dto.overlap.OverlapDto;
import skhu.sof14.hotthink.model.dto.overlap.OverlapElementDto;
import skhu.sof14.hotthink.model.dto.overlap.OverlapReadDto;
import skhu.sof14.hotthink.model.dto.post.Pagination;
import skhu.sof14.hotthink.model.dto.post.QnaListElementDto;
import skhu.sof14.hotthink.repository.OverlapRepository;
import skhu.sof14.hotthink.service.OverlapService;
import skhu.sof14.hotthink.service.PostService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class OverlapController {
    //    표절신고는 중복신고(여러번 신고) 가능하도록 구현함.
    @Autowired
    OverlapService overlapService;
    @Autowired
    PostService postService;


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

    @GetMapping("/report/overlap/list/admin")
    public String overlapList(Model model, Pagination page){
        List<OverlapElementDto> list = overlapService.findAllOverlap(page);
        model.addAttribute("list", list);
        int pageSize = page.getRecordCount()%10 > 0? page.getRecordCount()/10+1 : page.getRecordCount()/10;
        model.addAttribute("size", pageSize);
        model.addAttribute("page", page.getPage());
        model.addAttribute("hasNext", page.getPage() < pageSize); //현재페이지보다, 총페이지의 수가 크다면 다음으로 갈수있음
        model.addAttribute("hasPre", 1<page.getPage()); //1보다 크다면,
        return "overlapList_admin";
    }

    @GetMapping("/report/overlap/list/admin/read")
    public String overlapRead(@RequestParam Long id, Model model){
        OverlapReadDto dto = overlapService.findOverlapById(id);
        model.addAttribute("overlap", dto);
        return "overlap_read";
    }
}
