package skhu.sof14.hotthink.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import skhu.sof14.hotthink.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;
}
