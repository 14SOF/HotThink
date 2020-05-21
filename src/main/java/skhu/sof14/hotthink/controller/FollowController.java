package skhu.sof14.hotthink.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import skhu.sof14.hotthink.service.FollowService;


@Controller
public class FollowController {

    @Autowired
    FollowService followService;

    @PostMapping("/create/follow")
    public @ResponseBody void createFollowList(int id){
        System.out.println(id);
        followService.createFollow(id);
    }

    @GetMapping("/delete/follow")
    public @ResponseBody void deleteFollowList(int id){
        System.out.println(id);
        followService.deleteFollow(id);
    }



}
