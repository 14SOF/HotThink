package skhu.sof14.hotthink.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FreeThinkController {

    @Secured("ROLE_USER")
    @GetMapping("/freethinklist")
    public String freeThinkList(){
        return "freethink_list";
    }
}
