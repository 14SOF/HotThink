package skhu.sof14.hotthink.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import skhu.sof14.hotthink.model.dto.post.Pagination;
import skhu.sof14.hotthink.model.dto.post.PostListElementDto;
import skhu.sof14.hotthink.model.dto.user.UserCreateDto;
import skhu.sof14.hotthink.model.dto.user.UserDetailDto;
import skhu.sof14.hotthink.repository.PostRepository;
import skhu.sof14.hotthink.service.PostService;
import skhu.sof14.hotthink.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class GuestController {

    @Autowired
    UserService userService;
    @Autowired
    PostRepository postRepository;

    @GetMapping("home")
    public String index() {


        return "index";

    }

    @GetMapping("login")
    public String login() {
        return "login";
    }

    @PostMapping("login")
    public String loginFail(HttpServletRequest request, Model model){
        model.addAttribute("userId", request.getAttribute("userId"));
        return "login";
    }

    @GetMapping("create/user")
    public String signUp() {
        return "signup";
    }


    @PostMapping("create/user")
    public String create(UserCreateDto user, Model model) {
        UserDetailDto dto = userService.create(user);
        model.addAttribute("users", dto);
        return "signup_suc";
    }

    @GetMapping("user_delete")
    public String user_delete(Model model){
        model.addAttribute("userId", SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        return "user_delete";
    }
}
