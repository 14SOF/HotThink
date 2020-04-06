package skhu.sof14.hotthink;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/questions")
public class tempcontroller {

    @GetMapping("/qna")
    public String qna(){
        return "/qnaList";
    }

    @GetMapping("/form")
    public String form(){
        return "/qnaForm";
    }
}
