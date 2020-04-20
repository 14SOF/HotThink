package skhu.sof14.hotthink.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import skhu.sof14.hotthink.repository.UserRepository;
import skhu.sof14.hotthink.service.UserRegService;

@RestController
public class checkController {


    @Autowired
    private UserRegService userRegService;


    @GetMapping("idCheck")
    public String id_check(String id) {
        System.out.println(id);
        String str = userRegService.idCheck(id);
        return str;
    }

    @GetMapping("nickCheck")
    public String nick_check(String id){

        System.out.println(id);
        String str = userRegService.nickCheck(id);
        return str;
    }
}
