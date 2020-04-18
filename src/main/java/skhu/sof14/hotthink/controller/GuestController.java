package skhu.sof14.hotthink.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class GuestController {
    @GetMapping({"/", "home"})
    public String index(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(auth);
        if (!auth.getPrincipal().equals("anonymousUser")) model.addAttribute("test", auth.getPrincipal());
        return "index";
    }

    @PostMapping("home")
    public String home() {
        return "mypage";
    }

    @GetMapping("login")
    public String login() {
        return "login";
    }

    @GetMapping("signup")
    public String signUp() {
        return "signup";
    }

}
