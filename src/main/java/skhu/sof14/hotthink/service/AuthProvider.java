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
import skhu.sof14.hotthink.model.dto.UserDetail;
import skhu.sof14.hotthink.utils.EncryptionUtils;

import java.util.Collection;

@Component
public class AuthProvider implements AuthenticationProvider {

    @Autowired
    UserDetailService userDetailsService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String userId = authentication.getName();
        String userPassword = authentication.getCredentials().toString();

        UserDetail user = (UserDetail) userDetailsService.loadUserByUsername(userId);

        if(!user.getPassword().equals(EncryptionUtils.encryptMD5(userPassword))) throw new BadCredentialsException("패스워드가 일치하지 않습니다");
        return new UserToken(userId, userPassword, null, user);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

    public class UserToken extends UsernamePasswordAuthenticationToken {
        private static final long serialVersionUID = 1L;

        @Getter
        @Setter
        UserDetail user;

        public UserToken(Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities, UserDetail user) {
            super(principal, credentials, user.getAuthorities());
            this.user = user;
        }

        @Override
        public Object getDetails() {
            return user;
        }
    }

}
