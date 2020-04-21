package skhu.sof14.hotthink.controller;


import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import skhu.sof14.hotthink.model.dto.PostDto;
import skhu.sof14.hotthink.service.PostService;


@Controller
@AllArgsConstructor
public class PostController {
    @Autowired
    private PostService postService;

    @GetMapping("/list")
    public String list(){
        return "freethink_list";
    }

    @GetMapping("/write")
    public String write(){
        return "freethink_write";
    }

    @PostMapping("/post")
    public String write(PostDto postDto) {
        postService.savePost(postDto);

        return "redirect:/list";
    }
}
