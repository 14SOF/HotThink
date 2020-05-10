package skhu.sof14.hotthink.service;

import net.nurigo.java_sdk.api.Message;
import net.nurigo.java_sdk.exceptions.CoolsmsException;
import org.json.simple.JSONObject;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import skhu.sof14.hotthink.model.dto.Point.PointChargeDto;
import skhu.sof14.hotthink.model.dto.post.MyPostDto;
import skhu.sof14.hotthink.model.dto.post.Pagination;
import skhu.sof14.hotthink.model.dto.user.*;
import skhu.sof14.hotthink.model.entity.Point;
import skhu.sof14.hotthink.model.entity.User;
import skhu.sof14.hotthink.repository.PostRepository;
import skhu.sof14.hotthink.repository.UserRepository;
import skhu.sof14.hotthink.utils.EncryptionUtils;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    ModelMapper mapper;

    @Autowired
    PostRepository postRepository;

    @Autowired
    UserDetailService userDetailService;

    //유저 작성 게시판들
    public List<MyPostDto> findMyPost(Pagination pagination) {
        User user = new User();
        user.setId(getIdFromAuth());
        Type dtoListType = new TypeToken<List<MyPostDto>>() {
        }.getType();
        return mapper.map(postRepository.findAllByUser(user, pagination), dtoListType);

    }

    public String getNickFromAuth() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && !auth.getPrincipal().equals("anonymousUser")) {
            int id = ((UserLoginDto) auth.getDetails()).getId();
            return findNickById(id);
        }
        return "anonymousUser";
    }

    public static int getIdFromAuth() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        int id = ((UserBase) auth.getDetails()).getId();
        return id;
    }

    public UserDetailDto getUserDetailFromAuth() {
        User entity = userRepository.findUserById(getIdFromAuth());
        UserDetailDto dto = mapper.map(entity, UserDetailDto.class);
        return dto;
    }

    @Transactional
    public void updateUser(UserUpdateDto vo) {
        int id = getIdFromAuth();
        String nick = vo.getNick();
        String pw = vo.getUserPassword().equals("") ? "" : EncryptionUtils.encryptMD5(vo.getUserPassword());
        if (nick != "" && pw != "") userRepository.updateNickAndUserPassword(id, nick, pw);
        else if (nick == "") userRepository.updateUserPassword(id, pw);
        else userRepository.updateNick(id, nick);
    }

    public String findNickById(int id) {
        User entity = userRepository.findUserById(id);
        return entity.getNick();
    }

    public boolean nickDuplicationCheck(String nick) {
        User entity = userRepository.findUserByNick(nick);
        return entity == null;
    }

    public boolean idDuplicationCheck(String id) {
        User entity = userRepository.findUserByUserId(id);
        return entity == null;
    }

    public boolean pwCheck(String userPassword) { //회원탈퇴페이지 pw 일치 여부
        String userId = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserLoginDto user = (UserLoginDto) userDetailService.loadUserByUsername(userId);
//        System.out.println(user.getPassword());
        if (user.getPassword().equals(EncryptionUtils.encryptMD5(userPassword))) {
            return true;
        }
        return false;
    }

    public void deleteUser() {  //회원탈퇴버튼 누르면 요청되는 service , user의 status를 0으로 변경한다.
        int id = getIdFromAuth();
        userRepository.updateStatus(id);
    }


    public UserDetailDto create(UserCreateDto user) { //회원가입 : 회원정보를 DB에 저장
        User entity = mapper.map(user, User.class);
        entity.setUserPassword(EncryptionUtils.encryptMD5(user.getUserPassword()));
        entity.setStatus(true);
        entity = userRepository.save(entity);
        return mapper.map(entity, UserDetailDto.class);
    }


    public UserCreateDto findUserByUserId(String userId) {
        User entity = userRepository.findUserByUserId(userId);
        if (entity == null) return null;
        UserCreateDto dto = mapper.map(entity, UserCreateDto.class);
        System.out.println(dto);
        return dto;
    }

    public void certifiedPhoneNumber(String phoneNumber) {

        String api_key = "NCSBVON9BZGTHH2U";
        String api_secret = "CLFRQNF13AWV5VHXSMTFWZKIZRJISLK7";
        Message coolsms = new Message(api_key, api_secret);

        // 4 params(to, from, type, text) are mandatory. must be filled
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("to", phoneNumber);    // 수신전화번호
        params.put("from", "01020180103");    // 발신전화번호. 테스트시에는 발신,수신 둘다 본인 번호로 하면 됨
        params.put("type", "SMS");
        params.put("text", "핫띵크 휴대폰인증 테스트 메시지 : 인증번호는 [17171771] 입니다.");
        params.put("app_version", "test app 1.2"); // application name and version

        try {
            JSONObject obj = (JSONObject) coolsms.send(params);
            System.out.println(obj.toString());
        } catch (CoolsmsException e) {
            System.out.println(e.getMessage());
            System.out.println(e.getCode());
        }

    }

}
