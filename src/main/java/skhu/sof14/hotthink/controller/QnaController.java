package skhu.sof14.hotthink.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import skhu.sof14.hotthink.model.dto.post.QnaCreateDto;
import skhu.sof14.hotthink.model.entity.Post;
import skhu.sof14.hotthink.model.entity.User;
import skhu.sof14.hotthink.repository.PostRepository;
import skhu.sof14.hotthink.repository.UserRepository;
import skhu.sof14.hotthink.service.PostService;
import skhu.sof14.hotthink.service.UserService;
import org.modelmapper.ModelMapper;

import java.lang.reflect.Type;

@Controller
public class QnaController {


    @Autowired
    PostService postService;

    @Autowired
    UserService userService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    ModelMapper mapper;
    @Autowired
    PostRepository postRepository;


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
    public String qnaSubmit(String title, String content) {
        postService.createQna(new QnaCreateDto(title, content));
        return "qna_list";
    }
}
