package skhu.sof14.hotthink.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import skhu.sof14.hotthink.model.dto.user.UserBase;
import skhu.sof14.hotthink.model.dto.user.UserCreateDto;
import skhu.sof14.hotthink.model.dto.user.UserDetailDto;
import skhu.sof14.hotthink.model.dto.user.UserLoginDto;
import skhu.sof14.hotthink.model.entity.User;
import skhu.sof14.hotthink.model.dto.user.UserUpdateDto;
import skhu.sof14.hotthink.repository.UserRepository;
import skhu.sof14.hotthink.utils.EncryptionUtils;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    ModelMapper mapper;

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

    public boolean idDuplicationCheck(String id){
        User entity = userRepository.findUserByUserId(id);
        return entity == null;
    }

    public UserDetailDto create(UserCreateDto user) { //회원가입 , 회원 정보를 DB에 저장
        user.setUserPassword(EncryptionUtils.encryptMD5(user.getUserPassword()));
        User entity = mapper.map(user, User.class);
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


}
