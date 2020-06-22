package skhu.sof14.hotthink.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import skhu.sof14.hotthink.model.dto.post.MyPostDto;
import skhu.sof14.hotthink.model.dto.post.Pagination;
import skhu.sof14.hotthink.model.dto.post.PostTitleAndTypeDto;
import skhu.sof14.hotthink.model.dto.user.UserCreateDto;
import skhu.sof14.hotthink.model.dto.user.UserDetailDto;
import skhu.sof14.hotthink.model.dto.user.UserPostDto;
import skhu.sof14.hotthink.model.dto.user.UserUpdateDto;
import skhu.sof14.hotthink.service.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    KafkaService kafkaService;

    @Autowired
    PointService pointService;

    @Autowired
    FollowService followService;

    @Autowired
    PostService postService;

    @Autowired
    RateService rateService;


    @GetMapping("/user/mypage/home")
    public ModelAndView myPage(){
        ModelAndView modelAndView = new ModelAndView();
        UserDetailDto dto = userService.getUserDetailFromAuth();
        Map<String, String> attr = new HashMap<>();
        attr.put("userId", dto.getUserId());
        attr.put("userNick", dto.getNick());
        attr.put("userName", dto.getName());
        attr.put("userPhone", dto.getPhone());
        modelAndView.addObject("point", pointService.ChargeList());
        modelAndView.addObject("sum", pointService.amountSum());
        modelAndView.addObject("rateAvg", RateService.loginUserAvgRate);
        modelAndView.addObject("rate", RateService.rateDtoList);
        modelAndView.addAllObjects(attr);
        modelAndView.setViewName("mypage");
        return modelAndView;
    }

    @PutMapping("/update/user")
    public @ResponseBody Map<String, Boolean> updateUser(@RequestBody UserUpdateDto vo){
        userService.updateUser(vo);
        Map<String, Boolean> json = new HashMap<>();
        json.put("check", true);
        return json;
    }

    @GetMapping("/user/mypage/message")
    public String message() {
        return "mypage_message";
    }

    @GetMapping("/user/mypage/message/list")
    public @ResponseBody Map<String, Object> messageList(){
        Map<String, Object> json = new HashMap<>();
        json.put("me", UserService.getIdFromAuth());
        json.put("new", kafkaService.getNewMessages());
        json.put("list", kafkaService.findAllByUser());
        return json;
    }

    @GetMapping("/user/mypage/message/search")
    public @ResponseBody Map<String, List<UserPostDto>> searchUserNick(@RequestParam String nick){
        List<UserPostDto> userPostDtoList = userService.findAllByNickStartsWith(nick);
        Map<String, List<UserPostDto>> json = new HashMap<>();
        json.put("list", userPostDtoList);
        return json;
    }

    @PostMapping("/user/delete")
    public String deleteUser(Model model){
        userService.deleteUser();
        model.addAttribute("userId", SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        return "user_delete_success";

    }

    @PostMapping("create")
    public String create(UserCreateDto user, Model model) {
        userService.create(user);
        model.addAttribute("users", userService.findUserByUserId(user.getUserId()));
        return "signup_suc";
    }

    @GetMapping("/user/mypage/alarm")
    public String alarm() {
        return "mypage_alarm";
    }

    @GetMapping("/user/mypage/myboards")
    public String myBoards(Model model, Pagination page) {
        List<MyPostDto> dtoList = userService.findMyPost(page);
        for(int i=0; i<dtoList.size(); i++){
            model.addAttribute("item"+(i+1), dtoList.get(i));
        }

        int pageSize = page.getRecordCount()%4 > 0? page.getRecordCount()/4+1 : page.getRecordCount()/4;
        model.addAttribute("nowPage", page.getPage());
        model.addAttribute("pageSize", pageSize);

        return "mypage_myboards";
    }

    @GetMapping("/user")
    public ModelAndView userPage(@RequestParam int id){
        if (id == UserService.getIdFromAuth()) return myPage();
        ModelAndView modelAndView = new ModelAndView();
        Map<String, Object> followList = followService.followList(id);
        List<PostTitleAndTypeDto> postDtoList =   postService.findAllByUserId(id);
        modelAndView.addObject("id", id);
        modelAndView.addObject("nick", userService.findNickById(id));
        modelAndView.addObject("followerList", followList.get("followerList"));
        modelAndView.addObject("followingList", followList.get("followingList"));
        modelAndView.addObject("check", followList.get("check"));
        modelAndView.addObject("boardList", postDtoList);
        modelAndView.addObject("rate", rateService.findRateAvg(id));
        modelAndView.setViewName("user_page");
        return modelAndView;
    }

    @GetMapping("/user/mypage/follow")
    public String follow(Model model) {
        Map<String,Object> follow = followService.followList(UserService.getIdFromAuth());
        model.addAttribute("followerList", follow.get("followerList"));
        model.addAttribute("followingList", follow.get("followingList"));
        return "mypage_follow";
    }


}
