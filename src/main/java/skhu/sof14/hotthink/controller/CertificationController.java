package skhu.sof14.hotthink.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import skhu.sof14.hotthink.service.CertificationService;
import skhu.sof14.hotthink.service.UserService;

import java.util.Random;

@Controller
public class CertificationController {

    @Autowired
    CertificationService certificationService;

    @GetMapping("/check/sendSMS")
    public @ResponseBody
    String sendSMS(String phoneNumber) {
        Random rand  = new Random();
        String numStr = "";
        for(int i=0; i<4; i++) {
            String ran = Integer.toString(rand.nextInt(10));
            numStr+=ran;
        }
        System.out.println("수신자 번호 : " + phoneNumber);
        System.out.println("인증번호 : " + numStr);
        certificationService.certifiedPhoneNumber(phoneNumber,numStr);
        //↗ 이거 주석 해제하면 휴대폰인증누를때마다 내돈 20원씩나감 참고하세요^^
        return numStr;
    }

    @GetMapping("/update/phone")
    public @ResponseBody void updatePhone(String phoneNumber){
        System.out.println("넘어오나 " + phoneNumber);
        certificationService.updatePhoneNumber(phoneNumber);
    }
}
