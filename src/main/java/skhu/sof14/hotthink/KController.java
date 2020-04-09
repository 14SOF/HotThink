package skhu.sof14.hotthink;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class KController {

    @GetMapping("qna")
    public String qna(){
        return "qnaList";
    }

    @GetMapping("form")
    public String form(){
        return "qnaForm";
    }
    @GetMapping("login")
    public String login(){
        return "login";
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
}
