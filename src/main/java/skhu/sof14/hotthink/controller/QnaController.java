package skhu.sof14.hotthink.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import skhu.sof14.hotthink.model.dto.user.UserDetailDto;
import skhu.sof14.hotthink.model.entity.User;
import skhu.sof14.hotthink.service.PostService;
import skhu.sof14.hotthink.service.UserService;

@Controller
public class QnaController {


    @Autowired
    PostService postService;

    @Autowired
    UserService userService;



    @GetMapping("qnaForm")
    public String qnaForm() {
        return "qna_form";
    }

    @GetMapping("qna")
    public String qna() {
        return "qna_list";
    }


    @GetMapping("list/1")
    public String qnaPage() {
        return "qna_contents";
    }

    @GetMapping("list/1/update")
    public String qnaUpdate() {
        return "qna_updateForm";
    }

    @PostMapping("create/post/qna")
    public String qnaSubmit(String title, String content, Model model){
        int Useridx = UserService.getIdFromAuth();
        model.addAttribute("userNick", )

    }

}
