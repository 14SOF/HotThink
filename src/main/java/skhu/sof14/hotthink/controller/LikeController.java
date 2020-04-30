package skhu.sof14.hotthink.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import skhu.sof14.hotthink.service.LikeService;

@Controller
public class LikeController {

    @Autowired
    LikeService service;

    @PostMapping("create/like/post")
    public @ResponseBody int createPostLike(@RequestParam Long id){
        return service.createPostLike(id);
    }

    @DeleteMapping("delete/like/post")
    public @ResponseBody int deletePostLike(@RequestParam Long id){
        return service.deletePostLike(id);
    }
}
