package skhu.sof14.hotthink.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import skhu.sof14.hotthink.service.UserService;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Controller
public class CheckController {

    @Autowired
    private UserService userService;

    @GetMapping("/check/user/id")
    public @ResponseBody
    Map<String, Boolean> checkUserId(String id) {
        Map<String, Boolean> json = new HashMap<>();
        json.put("check", userService.idDuplicationCheck(id));
        return json;
    }

    @GetMapping("/check/user/nick")
    public @ResponseBody
    Map<String, Boolean> checkUserNick(String nick) {
        Map<String, Boolean> json = new HashMap<>();
        json.put("check", userService.nickDuplicationCheck(nick));
        return json;
    }

    @PostMapping("/check/user/pw")
    public @ResponseBody Map<String, Boolean> pw_check(String userPassword){
        Map<String, Boolean> json = new HashMap<>();
        json.put("check", userService.pwCheck(userPassword));
        return json;
    }



}