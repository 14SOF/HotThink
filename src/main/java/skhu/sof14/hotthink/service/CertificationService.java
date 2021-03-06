package skhu.sof14.hotthink.service;

import net.nurigo.java_sdk.api.Message;
import net.nurigo.java_sdk.exceptions.CoolsmsException;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import skhu.sof14.hotthink.repository.UserRepository;

import java.util.HashMap;
import java.util.Random;

@Service
public class CertificationService {
    @Autowired
    UserRepository userRepository;

    public void certifiedPhoneNumber(String phoneNumber, String cerNum) {

        String api_key = "NCSBVON9BZGTHH2U";
        String api_secret = "CLFRQNF13AWV5VHXSMTFWZKIZRJISLK7";
        Message coolsms = new Message(api_key, api_secret);

        // 4 params(to, from, type, text) are mandatory. must be filled
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("to", phoneNumber);    // 수신전화번호
        params.put("from", "01020180103");    // 발신전화번호. 테스트시에는 발신,수신 둘다 본인 번호로 하면 됨
        params.put("type", "SMS");
        params.put("text", "핫띵크 휴대폰인증  메시지 : 인증번호는" + "[" + cerNum + "]" + "입니다.");
        params.put("app_version", "test app 1.2"); // application name and version

        try {
            JSONObject obj = (JSONObject) coolsms.send(params);
            System.out.println(obj.toString());
        } catch (CoolsmsException e) {
            System.out.println(e.getMessage());
            System.out.println(e.getCode());
        }

    }

    public void updatePhoneNumber(String phoneNumber) {
        int id = UserService.getIdFromAuth();
        userRepository.updatePhone(phoneNumber, id);
    }

    public String createRandomNumber() {
        Random rand = new Random();
        String numStr = "";
        for (int i = 0; i < 4; i++) {
            String ran = Integer.toString(rand.nextInt(10));
            numStr += ran;
        }
        return numStr;
    }

}
