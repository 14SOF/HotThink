package skhu.sof14.hotthink.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import skhu.sof14.hotthink.service.MyAuthenticationProvider;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Autowired
    MyAuthenticationProvider authenticationProvider;

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
//                .antMatchers("/resources/**")
                .antMatchers("/style/**")
                .antMatchers("/icons/**")
                .antMatchers("/image/**")
                .antMatchers("/images/**")
                .antMatchers("/font/**");

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/signup").permitAll()
                .antMatchers("/login").permitAll()
                .antMatchers("/login_processing").permitAll();

//                .anyRequest().authenticated();

        http.csrf().disable();

        http.formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/login_processing")
                .usernameParameter("userId")
                .passwordParameter("userPassword")
                .successHandler(new AuthenticationSuccessHandler() {
                    @Override
                    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                                        Authentication authentication) throws IOException, ServletException {
                        redirectStrategy.sendRedirect(request, response, "/mypage");
                    }
                })
                .failureForwardUrl("/login?error");

        http.authenticationProvider(authenticationProvider);
    }

}
