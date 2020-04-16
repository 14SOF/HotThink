package skhu.sof14.hotthink.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import skhu.sof14.hotthink.model.entity.User;
import skhu.sof14.hotthink.repository.UserRepository;
import skhu.sof14.hotthink.utils.EncryptionUtils;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public User login(String userId, String userPassword){
        User entity = userRepository.findUserByUserId(userId);
        if(entity==null) return null;
        String pw = EncryptionUtils.encryptMD5(userPassword);
        if(!entity.getUserPassword().equals(pw)) return null;
        return entity;
    }
}
