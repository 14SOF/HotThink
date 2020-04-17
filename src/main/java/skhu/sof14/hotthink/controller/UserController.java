package skhu.sof14.hotthink.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import skhu.sof14.hotthink.model.User;
import skhu.sof14.hotthink.repository.UserRepository;


@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("create")
    public String create(User user, Model model) {
        userRepository.save(user);
        model.addAttribute("users", userRepository.findById(user.user_idx).get());
        return "signup_success";
    }

//    @GetMapping("signup_success")
//    public String signup_success(Model model){
//        model.addAttribute("users", userRepository.findById());
//        System.out.println(model);
//        return "signup_success";
//    }
}
