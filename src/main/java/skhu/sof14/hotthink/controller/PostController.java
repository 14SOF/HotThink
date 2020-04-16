package skhu.sof14.hotthink.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import skhu.sof14.hotthink.model.dto.PostDto;
import skhu.sof14.hotthink.repository.PostRepository;
import skhu.sof14.hotthink.service.PostService;


@Controller
public class PostController {
    private PostService postService;
    @Autowired
    private PostRepository postRepository;

    @GetMapping("write")
    public String write(){
        return "freethink_write";
    }

    @PostMapping("post")
    public String freeThinkWrite(PostDto postDto) {
        postService.savePost(postDto);

        return "freethink_list";
    }
}
