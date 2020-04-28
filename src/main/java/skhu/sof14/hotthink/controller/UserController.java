package skhu.sof14.hotthink.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import skhu.sof14.hotthink.model.dto.UserCreateDTO;
import skhu.sof14.hotthink.service.UserService;

import skhu.sof14.hotthink.model.dto.UserDetailDto;
import skhu.sof14.hotthink.model.vo.UserUpdateVo;

import java.util.HashMap;
import java.util.Map;

@Controller
public class UserController {

    @Autowired
    UserService userService;

    // TODO: 2020-04-21
    // 마이페이지 조회를 rest에 맞게 mypage/users/1 을 할 것인지..
    @GetMapping("mypage")
    public String myPage(Model model){

        UserDetailDto dto = userService.getUserDetailFromAuth();

        Map<String, String> attr = new HashMap<>();
        attr.put("userId", dto.getUserId());
        attr.put("userNick", dto.getNick());
        attr.put("userName", dto.getName());
        attr.put("userPhone", dto.getPhone());
        model.addAllAttributes(attr);
        return "mypage";
    }

    @GetMapping("nickCheck")
    public @ResponseBody Map<String, Boolean> nick_check(String nick) {
        Map<String, Boolean> json = new HashMap<>();
        json.put("check", userService.nickDuplicationCheck(nick));
        return json;
    }

    @PostMapping("check/user/pw")
    public @ResponseBody Map<String, Boolean> pw_check(String userPassword){
        Map<String, Boolean> json = new HashMap<>();
        json.put("check", userService.pwCheck(userPassword));
        return json;
    }


    @PutMapping("update/user")
    public @ResponseBody Map<String, Boolean> updateUser(@RequestBody UserUpdateVo vo){
        userService.updateUser(vo);
        Map<String, Boolean> json = new HashMap<>();
        json.put("check", true);
        return json;
    }

    @PostMapping("user/delete")
    public String deleteUser(Model model){
        userService.deleteUser();
        model.addAttribute("userId", SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        return "user_delete_success";

    }

    @PostMapping("create")
    public String create(UserCreateDTO user, Model model) {
        userService.create(user);
        model.addAttribute("users", userService.findUserByUserId(user.getUserId()));
        return "signup_suc";
    }




}
