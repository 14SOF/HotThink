package skhu.sof14.hotthink;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TempController {

    @GetMapping("login")
    public String login(){
        return "login";
    }
}
