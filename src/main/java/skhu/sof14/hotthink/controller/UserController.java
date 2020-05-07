package skhu.sof14.hotthink.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import skhu.sof14.hotthink.model.dto.post.MyPostDto;
import skhu.sof14.hotthink.model.dto.post.Pagination;
import skhu.sof14.hotthink.model.dto.user.UserCreateDto;
import skhu.sof14.hotthink.model.entity.Point;
import skhu.sof14.hotthink.model.entity.User;
import skhu.sof14.hotthink.repository.PointRepository;
import skhu.sof14.hotthink.repository.UserRepository;
import skhu.sof14.hotthink.service.PointService;
import skhu.sof14.hotthink.service.UserService;

import skhu.sof14.hotthink.model.dto.user.UserDetailDto;
import skhu.sof14.hotthink.model.dto.user.UserUpdateDto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    PointRepository pointRepository;

    @Autowired
    UserRepository userRepository;

    @GetMapping("/user/mypage/home")
    public String myPage(Model model){

        UserDetailDto dto = userService.getUserDetailFromAuth();

        User user=userRepository.findUserById(UserService.getIdFromAuth());


        Map<String, String> attr = new HashMap<>();
        attr.put("userId", dto.getUserId());
        attr.put("userNick", dto.getNick());
        attr.put("userName", dto.getName());
        attr.put("userPhone", dto.getPhone());
        model.addAttribute("point", pointRepository.findAllByUser(user));
        model.addAttribute("sum", pointRepository.amountSum(user));
        model.addAllAttributes(attr);
        return "mypage";
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

    @PostMapping("user/delete")
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

    @GetMapping("/user/mypage/follow")
    public String follow() {
        return "mypage_follow";
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

}
