package skhu.sof14.hotthink.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import skhu.sof14.hotthink.model.dto.post.PostCreateDto;
import skhu.sof14.hotthink.service.PostService;

import java.util.HashMap;
import java.util.Map;

@Controller
public class FreeThinkController {

    @Autowired
    PostService postService;

    @GetMapping("/read/post/free/list")
    public String freeThinkList() {
        return "freethink_list";
    }

    @GetMapping("/create/post/free")
    public String createFreeThinkView() {
        return "freethink_write";
    }

    @GetMapping("/read/post/free")
    public String readFreeThink(@RequestParam Long id, Model model){
        model.addAttribute("free",postService.findPostById(id));
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
