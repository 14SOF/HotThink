package skhu.sof14.hotthink.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class GuestController {
    @GetMapping({"/", "home"})
    @PostMapping("home")
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

    @GetMapping("signup")
    public String signUp() {
        return "signup";
    }

    @GetMapping("user_delete")
    public String user_delete(Model model){
        model.addAttribute("userId", SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        return "user_delete";
    }

}
