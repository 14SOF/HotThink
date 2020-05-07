package skhu.sof14.hotthink.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import skhu.sof14.hotthink.model.dto.Point.PointChargeDto;
import skhu.sof14.hotthink.repository.UserRepository;
import skhu.sof14.hotthink.service.PointService;
import skhu.sof14.hotthink.service.UserService;

@Controller
public class PointController {

    @Autowired
    UserService userService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    PointService pointService;



    @GetMapping("/user/mypage/charge")
    public String chargePage(Model model){

        int id = UserService.getIdFromAuth();
        model.addAttribute("user", userRepository.findUserById(id));

        return "mypage_charge";
    }


    @GetMapping("/user/mypage/charge/point")
    public @ResponseBody void chargePoint(Long amount){
        System.out.println(amount);
        int id  = UserService.getIdFromAuth();
        pointService.chargePoint(new PointChargeDto(amount), id);
    }
}