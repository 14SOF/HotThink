package skhu.sof14.hotthink.service;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import skhu.sof14.hotthink.model.dto.UserLoginDto;
import skhu.sof14.hotthink.model.entity.User;
import skhu.sof14.hotthink.repository.UserRepository;

@Slf4j
@Service
public class UserDetailService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User entity = userRepository.findUserByUserId(username); // 유저 아이디를 찾음.
        if(entity == null) throw new UsernameNotFoundException("해당 아이디를 가진 유저를 찾을 수 없습니다");
        return modelMapper.map(entity, UserLoginDto.class); //UserLoginDto : userId,password,status를 보내줌.
    }

}
