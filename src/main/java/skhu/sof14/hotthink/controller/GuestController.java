package skhu.sof14.hotthink.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GuestController {
    @GetMapping("login")
    public String login(){
        return "login";
    }

    @GetMapping("signup")
    public String signUp(){
        return "signup";
    }
}
