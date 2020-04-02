package skhu.sof14.hotthink;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TempController {

    @GetMapping("login")
    public String login(){
        return "login";
    }

    @GetMapping("signup")
    public String signUp(){
        return "signup";
    }

    @GetMapping("404")
    public String pageNotFound(){
        return "404";
    }
    @GetMapping("mypage")
    public String myPage(Model model){
        model.addAttribute("test", "김영곤");
        return "mypage";
    }
}
