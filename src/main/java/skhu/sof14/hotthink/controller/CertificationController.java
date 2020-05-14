package skhu.sof14.hotthink.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import skhu.sof14.hotthink.service.CertificationService;


@Controller
public class CertificationController {

    @Autowired
    CertificationService certificationService;

    @GetMapping("/check/sendSMS")
    public @ResponseBody
    String sendSMS(String phoneNumber) {

        String numStr = certificationService.createRandomNumber();
        System.out.println("수신자 번호 : " + phoneNumber);
        System.out.println("인증번호 : " + numStr);
        certificationService.certifiedPhoneNumber(phoneNumber,numStr);
        return numStr;
    }

    @GetMapping("/update/phone")
    public @ResponseBody void updatePhone(String phoneNumber){
        System.out.println("넘어오나 " + phoneNumber);
        certificationService.updatePhoneNumber(phoneNumber);
    }
}
