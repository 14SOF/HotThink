package skhu.sof14.hotthink.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import skhu.sof14.hotthink.model.User;
import skhu.sof14.hotthink.repository.UserRepository;
import skhu.sof14.hotthink.service.UserRegService;

import java.sql.SQLOutput;


@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserRegService userRegService;

    @PostMapping("create")
    public String create(User user, Model model) {
        userRegService.create(user);
        model.addAttribute("users", userRepository.findById(user.getId()).get());
        return "signup_success";
    }

    @GetMapping("idCheck")
    public String id_check(String id) {
        System.out.println(id);
        String str = userRegService.idCheck(id);
        return str;
    }

}
