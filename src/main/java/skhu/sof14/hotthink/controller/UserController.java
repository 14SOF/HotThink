package skhu.sof14.hotthink.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import skhu.sof14.hotthink.model.dto.UserCreateDTO;
import skhu.sof14.hotthink.service.UserRegService;
import skhu.sof14.hotthink.service.UserService;


@Controller
public class UserController {

    @Autowired
    private UserRegService userRegService;

    @Autowired
    private UserService userService;

    @PostMapping("create")
    public String create(UserCreateDTO user, Model model) {
        userRegService.create(user);
        model.addAttribute("users", userService.findUserByUserId(user.getUserId()));
        return "signup_suc";
    }


}
