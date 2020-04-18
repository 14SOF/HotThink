package skhu.sof14.hotthink.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import skhu.sof14.hotthink.model.User;
import skhu.sof14.hotthink.repository.UserRepository;
import skhu.sof14.hotthink.service.UserRegService;

import java.sql.SQLOutput;


@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserRegService userRegService;

    @PostMapping("create")
    public String create(User user, Model model) {
        userRegService.create(user);
        model.addAttribute("users", userRepository.findById(user.userIdx).get());
        return "signup_success";
    }

//    @PostMapping("idCheck")
//    public @ResponseBody int id_check(@RequestParam("id") String id) {
//        System.out.println(id);
//        int ck = userRegService.idCheck(id);
//        return ck;
//    }

}
