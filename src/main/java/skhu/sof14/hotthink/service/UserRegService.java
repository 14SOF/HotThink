package skhu.sof14.hotthink.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import skhu.sof14.hotthink.model.User;
import skhu.sof14.hotthink.repository.UserRepository;


@RequiredArgsConstructor
@Service
public class UserRegService {
    @Autowired
    private final UserRepository userRepository;

    public User create(User user) {
        return userRepository.save(user);
    }

    public String idCheck(String userId){
        System.out.println(userRepository.findUserByUserId(userId));

        if(userRepository.findUserByUserId(userId) == null){
            return "YES";
        }else {
            return "NO";
        }


    }


}
