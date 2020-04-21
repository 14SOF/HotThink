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

    public UserCreateDTO findUserByUserId(String userId){
        User entity = userRepository.findUserByUserId(userId);
        if(entity == null) return null;
        UserCreateDTO dto = modelMapper.map(entity, UserCreateDTO.class);
        return dto;
    }
}
