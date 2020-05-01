package skhu.sof14.hotthink.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import skhu.sof14.hotthink.service.UserService;

import java.util.HashMap;
import java.util.Map;

@RestController
public class CheckController {

    @Autowired
    private UserService userService;

    // TODO: 2020-04-22 : check url 시큐리티 컨트롤되게 수정 예정
    @GetMapping("idCheck")
    public String id_check(String id) {
        System.out.println(id);
        String str = userService.idCheck(id);
        return str;
    }



//    @PostMapping("check/user/pw")
//    public @ResponseBody
//    Map<String, Boolean> checkUserPw(String userPassword) {
//        Map<String, Boolean> json = new HashMap<>();
//        json.put("check", )
//    }
}
