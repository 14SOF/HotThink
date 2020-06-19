package skhu.sof14.hotthink.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import skhu.sof14.hotthink.model.dto.user.MailDto;
import skhu.sof14.hotthink.service.SendEmailService;
import skhu.sof14.hotthink.service.UserService;

import java.util.HashMap;
import java.util.Map;

@Controller
public class CheckController {

    @Autowired
    private UserService userService;

    @Autowired
    SendEmailService sendEmailService;

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

    @GetMapping("/check/findPw")
    public @ResponseBody Map<String, Boolean> pw_find(String userEmail, String userName){
        Map<String,Boolean> json = new HashMap<>();
        boolean pwFindCheck = userService.userEmailCheck(userEmail,userName);

        System.out.println(pwFindCheck);
        json.put("check", pwFindCheck);

        return json;
    }

    @PostMapping("/check/findPw/sendEmail")
    public @ResponseBody void sendEmail(String userEmail, String userName){
        MailDto dto = sendEmailService.createMailAndChangePassword(userEmail, userName);
        sendEmailService.mailSend(dto);

    }






}
