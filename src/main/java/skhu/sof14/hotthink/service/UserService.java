package skhu.sof14.hotthink.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import skhu.sof14.hotthink.model.dto.UserBase;
import skhu.sof14.hotthink.model.dto.UserDetailDto;
import skhu.sof14.hotthink.model.dto.UserLoginDto;
import skhu.sof14.hotthink.model.entity.User;
import skhu.sof14.hotthink.model.vo.UserUpdateVo;
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

    public int getIdFromAuth() {
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
    public void updateUser(UserUpdateVo vo) {
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

    public boolean nickCheck(String nick) {
        User entity = userRepository.findUserByNick(nick);
        return entity == null;
    }
}
