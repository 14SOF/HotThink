package skhu.sof14.hotthink.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import skhu.sof14.hotthink.model.dto.post.Pagination;
import skhu.sof14.hotthink.model.dto.post.PostCreateDto;
import skhu.sof14.hotthink.model.dto.post.PostListElementDto;
import skhu.sof14.hotthink.model.dto.post.PostReadDto;
import skhu.sof14.hotthink.service.PostService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class FreeBoardController {

    @Autowired
    PostService postService;

    @GetMapping("/read/post/freeboard/list")
    public String freeBoardListView(Model model, Pagination page) {
        List<PostListElementDto> list = postService.findAllPage(page,"자게");
        model.addAttribute("list", list);
        int pageSize = page.getRecordCount()%10 > 0? page.getRecordCount()/10+1 : page.getRecordCount()/10;
        model.addAttribute("size", pageSize);
        model.addAttribute("page", page.getPage());
        model.addAttribute("hasNext", page.getPage()<pageSize);
        model.addAttribute("hasPre", 1<page.getPage());
        return "freeboard_list";
    }

    @GetMapping("/create/post/freeboard")
    public String createFreeBoardView() {
        return "freeboard_write";
    }

    @GetMapping("/read/post/freeboard")
    public String readFreeBoard(@RequestParam Long id, Model model){
        PostReadDto dto = postService.findPostById(id);
        model.addAttribute("freeboard", dto);
        return "freeboard_read";
    }


    @PostMapping("/create/post/freeboard")
    public @ResponseBody
    Map<String, Long> createFreeBoard(@RequestBody PostCreateDto dto) {
        Long id = postService.createFreeboard(dto);
        Map<String, Long> json = new HashMap<>();
        json.put("id", id);
        return json;
    }

}
