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
public class FreeThinkController {

    @Autowired
    PostService postService;

    @GetMapping("/read/post/free/list")
    public String freeThinkListView(Model model, Pagination page) {
        System.out.println(page);
        List<PostListElementDto> list = postService.findAllPage(page,"프리");
        model.addAttribute("list", list);
        int pageSize = page.getRecordCount()%10 > 0? page.getRecordCount()/10+1 : page.getRecordCount()/10;
        model.addAttribute("size", pageSize);
        model.addAttribute("page", page.getPage());
        model.addAttribute("hasNext", page.getPage()<pageSize);
        model.addAttribute("hasPre", 1<page.getPage());
        return "freethink_list";
    }

    @GetMapping("/create/post/free")
    public String createFreeThinkView() {
        return "freethink_write";
    }

    @GetMapping("/read/post/free")
    public String readFreeThink(@RequestParam Long id, Model model){
        PostReadDto dto = postService.findPostById(id);
        model.addAttribute("free", dto);
        return "freethink_read";
    }

    @PostMapping("/create/post/free")
    public @ResponseBody
    Map<String, Long> createFreeThink(@RequestBody PostCreateDto dto) {
        Long id = postService.createFree(dto);
        Map<String, Long> json = new HashMap<>();
        json.put("id", id);
        return json;
    }
}