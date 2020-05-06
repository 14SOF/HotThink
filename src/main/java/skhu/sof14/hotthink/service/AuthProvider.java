package skhu.sof14.hotthink.service;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;
import skhu.sof14.hotthink.model.dto.user.UserLoginDto;
import skhu.sof14.hotthink.utils.EncryptionUtils;

import java.util.Collection;

@Component
public class AuthProvider implements AuthenticationProvider {

    @Autowired
    UserDetailService userDetailsService;

    //authenticate에서 유저가입력한 pw와 대조
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String userId = authentication.getName(); //login.html에서 받아온 userId와
        String userPassword = authentication.getCredentials().toString(); //userPassword

        UserLoginDto user = (UserLoginDto) userDetailsService.loadUserByUsername(userId); //userId가 있으면 loginDto정보 리턴받는 user

        if(!user.getPassword().equals(EncryptionUtils.encryptMD5(userPassword))) throw new BadCredentialsException("패스워드가 일치하지 않습니다");
        return new UserToken(userId, userPassword, null, user); //UserToken에 userId와 userPassword, user(loginDto , id,pw,status)
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

    public class UserToken extends UsernamePasswordAuthenticationToken {
        private static final long serialVersionUID = 1L;

        @Getter
        @Setter
        UserLoginDto user;

        public UserToken(Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities, UserLoginDto user) {
            super(principal, credentials, user.getAuthorities());
            this.user = user;
            //principal : userId
            //credentials : password
        }

        @Override
        public Object getDetails() {
            return user;
        }
    }

}
