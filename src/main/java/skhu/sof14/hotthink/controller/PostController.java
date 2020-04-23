package skhu.sof14.hotthink.controller;


import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import skhu.sof14.hotthink.model.dto.PostDto;
import skhu.sof14.hotthink.service.PostService;

import java.util.List;

@Controller
@AllArgsConstructor
public class PostController {
    @Autowired
    private PostService postService;

    @GetMapping("/post")
    public String write(){
        return "freethink_write";
    }

    @PostMapping("/post")
    public String write(PostDto postDto, Model model) {
        postService.savePost(postDto);
        List<PostDto> postList = postService.getPostlist();
        model.addAttribute("postList", postList);

        return "redirect:/freethink_list";
    }

}
