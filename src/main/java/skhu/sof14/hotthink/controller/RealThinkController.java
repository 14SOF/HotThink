package skhu.sof14.hotthink.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import skhu.sof14.hotthink.model.dto.post.Pagination;
import skhu.sof14.hotthink.model.dto.post.PostListElementDto;
import skhu.sof14.hotthink.model.dto.post.PostReadDto;
import skhu.sof14.hotthink.model.dto.post.PostUpdateDto;
import skhu.sof14.hotthink.service.PostService;

import java.util.List;


@Controller
public class RealThinkController {

    @Autowired
    PostService postService;

    @GetMapping("/read/post/real/list")
    public String realThinkListView(Model model, Pagination page) {
        List<PostListElementDto> list = postService.findAllPage(page,"리얼");
        model.addAttribute("list", list);
        int pageSize = page.getRecordCount()%5 > 0? page.getRecordCount()/5+1 : page.getRecordCount()/5;
        model.addAttribute("size", pageSize);
        model.addAttribute("page", page.getPage());
        model.addAttribute("hasNext", page.getPage()<pageSize);
        model.addAttribute("hasPre", 1<page.getPage());
        return "realthink_list";
    }

    @GetMapping("/create/post/real")
    public String createRealThinkView(@RequestParam Long id, Model model) {
        PostReadDto dto = postService.findPostById(id);
        if(dto==null || !dto.getType().equals("핫")) return "/error/404";
        else{
            model.addAttribute("id", id);
            return "realthink_write";
        }
    }

    @PutMapping("/create/post/real")
    public ResponseEntity<String> createRealThink(@RequestParam Long id, @RequestBody PostUpdateDto dto){
        postService.createReal(id, dto);
        return ResponseEntity.ok().build();
    }
}
