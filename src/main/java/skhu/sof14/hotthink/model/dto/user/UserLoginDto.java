package skhu.sof14.hotthink.model.dto.user;

import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@ToString
public class UserLoginDto extends UserBase implements UserDetails{
    @Setter
    private String userId;
    @Setter
    private String userPassword;
    @Setter
    private boolean status;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {  //계정이 갖고있는 권한목록을 리턴한다.
        List<GrantedAuthority> auth = new ArrayList<>();
        auth.add(new SimpleGrantedAuthority("ROLE_USER"));
        return auth;
    }

    @Override
    public String getPassword() {
        return this.userPassword;
    } //계정의 패스워드를 리턴한다.

    @Override
    public String getUsername() {
        return this.userId;
    }  // 계정의 userId를 리턴한다.

    @Override
    public boolean isAccountNonExpired() {
        return this.status;
    } //계정의 만료여부를 리턴한다.(true: 만료되지 않음)

    @Override
    public boolean isAccountNonLocked() {
        return this.status;
    } //계정의 lock 여부(잠겨있는지) 를 리턴한다. (true: 잠겨있지 않음)

    @Override
    public boolean isCredentialsNonExpired() {
        return this.status;
    } // 계정의 패스워드 만료 여부를 리턴한다. (true: 만료되지 않음)

    @Override
    public boolean isEnabled() {
        return this.status;
    } //계정이 사용 가능한지 여부를 리턴한다. (true: 사용 가능)
}
