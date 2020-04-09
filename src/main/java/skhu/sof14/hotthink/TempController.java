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

    @GetMapping("freethink")
    public String freeThink(){
        return "freethinklist";
    }

    @GetMapping("hotthink")
    public String hotThink(){
        return "hotthink";
    }

    @GetMapping("qna")
    public String qna(){
        return "qnaList";
    }

    @GetMapping("form")
    public String form(){
        return "qnaForm";
    }

    @GetMapping("free")
    public  String free(){
        return "freeThink";
    }
    @GetMapping("footer1")
    public String footer1(){
        return "footer1";
    }
    @GetMapping("faq")
    public String faq(){
        return "FAQ";
    }
    @GetMapping("operation")
    public String op(){
        return "operation";
    }
    @GetMapping("thinkDetails")
    public String thinkDetails(){
        return "thinkDetails";
    }

}
