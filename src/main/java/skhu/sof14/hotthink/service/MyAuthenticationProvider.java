package skhu.sof14.hotthink.service;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;
import skhu.sof14.hotthink.model.entity.User;

@Component
public class MyAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    UserService userService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String userId = authentication.getName();
        String userPassword = authentication.getCredentials().toString();
        User entity = userService.login(userId, userPassword);
        if (entity == null) return null;
        System.out.println("로그인성공");
        return new UserToken(userId, userPassword, entity);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

    public class UserToken extends UsernamePasswordAuthenticationToken {
        private static final long serialVersionUID = 1L;

        @Getter
        @Setter
        User user;

        public UserToken(String userId, String userPassword, User user){
            super(userId, userPassword);
            this.user = user;
        }
    }
}
