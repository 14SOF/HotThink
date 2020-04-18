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

//    public int idCheck(String userId){
//        System.out.println(userRepository.findByUserId(userId));
//        if(userRepository.findByUserId(userId) == null){
//            return 0;
//        }else if(userId.equals("")){
//            return 1;
//        }
//        else {
//            return 1;
//        }
//
//    }


}
