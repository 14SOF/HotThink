package skhu.sof14.hotthink.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import skhu.sof14.hotthink.model.dto.UserCreateDTO;
import skhu.sof14.hotthink.model.entity.User;
import skhu.sof14.hotthink.repository.UserRepository;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    ModelMapper modelMapper;

    public User create(UserCreateDTO user) { //회원가입 , 회원 정보를 DB에 저장
        System.out.println("dto" + user.toString());
        User entity = new User();
        entity.setUserId(user.getUserId());
        entity.setName(user.getName());
        entity.setNick(user.getNick());
        entity.setUserPassword(user.getUserPassword());
        System.out.println("엔티티" + entity);
        return userRepository.save(entity);
    }

    public String idCheck(String userId) {
        System.out.println(userRepository.findUserByUserId(userId));

        if (userRepository.findUserByUserId(userId) == null) {
            return "YES";
        } else {
            return "NO";
        }
    }

    public String nickCheck(String nick) {
        if (userRepository.findUserByNick(nick) == null) {
            return "YES";
        } else {
            return "NO";
        }
    }


    public UserCreateDTO findUserByUserId(String userId) {
        User entity = userRepository.findUserByUserId(userId);
        if (entity == null) return null;
        UserCreateDTO dto = modelMapper.map(entity, UserCreateDTO.class);
        return dto;
    }


}
