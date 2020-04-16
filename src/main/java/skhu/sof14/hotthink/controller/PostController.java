package skhu.sof14.hotthink.controller;


import org.springframework.web.bind.annotation.GetMapping;
import skhu.sof14.hotthink.service.PostService;


public class PostController {
    private PostService memberService;

    @GetMapping("freethinkwrite")
    public String freeThinkWrite(){
        return "freethink_write";
    }

    @GetMapping("freethinklist")
    public String freeThinkList(){
        return "freethink_list";
    }
}
