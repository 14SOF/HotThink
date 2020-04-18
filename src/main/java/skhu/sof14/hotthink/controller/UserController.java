package skhu.sof14.hotthink.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {
    @GetMapping("mypage")
    public String myPage(Model model){
        model.addAttribute("test", "김영곤");
        return "mypage";
    }
}
