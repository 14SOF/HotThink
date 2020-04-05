package skhu.sof14.hotthink;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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

    @GetMapping("message")
    public String message(){
        return "message";
    }

    @GetMapping("alarm")
    public String alarm(){
        return "alarm";
    }

    @GetMapping("follow")
    public String follow(){
        return "follow";
    }

    @GetMapping("myboards")
    public String myBoards(){
        return "myboards";
    }

    @GetMapping("hotthink")
    public String hotThink(){
        return "hotthink";
    }
}
