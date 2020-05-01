package skhu.sof14.hotthink.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import skhu.sof14.hotthink.model.dto.post.Pagination;
import skhu.sof14.hotthink.model.dto.post.QnaCreateDto;
import skhu.sof14.hotthink.model.dto.post.QnaListElementDto;
import skhu.sof14.hotthink.model.dto.post.QnaReadDto;
import skhu.sof14.hotthink.model.entity.Post;
import skhu.sof14.hotthink.model.entity.User;
import skhu.sof14.hotthink.repository.PostRepository;
import skhu.sof14.hotthink.repository.UserRepository;
import skhu.sof14.hotthink.service.PostService;
import skhu.sof14.hotthink.service.UserService;
import org.modelmapper.ModelMapper;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.List;

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


    @GetMapping("create/post/qna")
    public String qnaForm() {
        return "qna_form";
    }

//    @GetMapping("qna")
//    public String qna() {
//        return "qna_list";
//    }


    @GetMapping("list/1")
    public String qnaPage() {
        return "qna_contents";
    }

    @GetMapping("list/1/update")
    public String qnaUpdate() {
        return "qna_updateForm";
    }

    @PostMapping("create/post/qna") //qna 글 등록 완료시 qna_list로 이동.
    public String qnaSubmit(String title, String content) {
        postService.createQna(new QnaCreateDto(title, content, LocalDateTime.now()));
        return "redirect:/read/post/qna/list?page=1";
    }

    @GetMapping("read/post/qna/list")
    public String qnaListView(Model model, Pagination page){
        List<QnaListElementDto> list = postService.findAllQna(page);
        model.addAttribute("list", list);
        int pageSize = page.getRecordCount()%10 > 0? page.getRecordCount()/10+1 : page.getRecordCount()/10;
        model.addAttribute("size", pageSize);
        model.addAttribute("page", page.getPage());
        model.addAttribute("hasNext", page.getPage() <pageSize); //현재페이지보다, 총페이지의 수가 크다면 다음으로 갈수있음
        model.addAttribute("hasPre", 1<page.getPage()); //1보다 크다면,
        return "qna_list";
    }

    @GetMapping("read/post/qna")
    public String readQna(@RequestParam Long id, Model model){
        QnaReadDto dto = postService.findPostById(id);
        model.addAttribute("qna", dto);
        return "qna_contents";
    }


}
