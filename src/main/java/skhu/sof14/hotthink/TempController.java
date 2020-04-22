package skhu.sof14.hotthink;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TempController {


    @GetMapping("404")
    public String pageNotFound(){
        return "404";
    }


    @GetMapping("message")
    public String message(){
        return "mypage_message";
    }

    @GetMapping("alarm")
    public String alarm(){
        return "mypage_alarm";
    }

    @GetMapping("follow")
    public String follow(){
        return "mypage_follow";
    }

    @GetMapping("myboards")
    public String myBoards(){
        return "mypage_myboards";
    }

    @GetMapping("freethink")
    public String freeThink(){
        return "freethink_read";
    }


    @GetMapping("freethinkwrite")
    public String freeThinkWrite(){
        return "freethink_write";
    }

    @GetMapping("realthinklist")
    public String realThinkList(){
        return "realthink_list";
    }

    @GetMapping("realthinkwrite")
    public String realThinkWrite(){
        return "realthink_write";
    }

    @GetMapping("hotthink")
    public String hotThink(){
        return "hotthink_list";
    }

    @GetMapping("realthink")
    public String realThink(){
        return "realthink_read";
    }

    @GetMapping("qna")
    public String qna(){
        return "qna_list";
    }

    @GetMapping("form")
    public String form(){
        return "qna_form";
    }

    @GetMapping("free")
    public  String free(){
        return "freeThink";
    }
    @GetMapping("footer1")
    public String footer1(){
        return "footer_policy";
    }
    @GetMapping("faq")
    public String faq(){
        return "footer_faq";
    }
    @GetMapping("operation")
    public String op(){
        return "footer_operation";
    }


//    qna 글 내용 test  실제구현은 id값으로
    @GetMapping("list/1")
    public String qnaPage(){
        return "qna_contents";
    }

    @GetMapping("list/1/update")
    public String qnaUpdate() {
        return "qna_updateForm";
    }

    @GetMapping("producer")
    public String producer() {
        return "footer_producer";
    }

    @GetMapping("realThinkDetails")
    public String realThinkDetails(){
        return "realThinkDetails";
    }

    @GetMapping("thinkMake")
    public String thinkMake(){
        return "thinkMake";
    }

}
