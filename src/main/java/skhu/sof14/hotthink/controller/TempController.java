package skhu.sof14.hotthink.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TempController {


    @GetMapping("form")
    public String form() {
        return "qna_form";
    }

    @GetMapping("free")
    public String free() {
        return "freeThink";
    }

    @GetMapping("footer1")
    public String footer1() {
        return "footer_policy";
    }

    @GetMapping("faq")
    public String faq() {
        return "footer_faq";
    }

    @GetMapping("operation")
    public String op() {
        return "footer_operation";
    }




    @GetMapping("producer")
    public String producer() {
        return "footer_producer";
    }

    @GetMapping("realThinkDetails")
    public String realThinkDetails() {
        return "realThinkDetails";
    }

    @GetMapping("thinkMake")
    public String thinkMake() {
        return "thinkMake";
    }

    @GetMapping("temptest")
    public String temptest(){
        return "temptest";
    }


//    @GetMapping("signup_success")
//    public String signup_success() {
//        return "signup_success";
//    }

    @GetMapping("testCharge")
    public String testCharge(){
        return "testone";
    }

}
