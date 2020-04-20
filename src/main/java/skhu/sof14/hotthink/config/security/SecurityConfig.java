package skhu.sof14.hotthink.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.firewall.DefaultHttpFirewall;
import org.springframework.security.web.firewall.HttpFirewall;
import skhu.sof14.hotthink.model.dto.UserDetail;
import skhu.sof14.hotthink.service.AuthProvider;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    public static String getUserNickFromAuth() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && !auth.getPrincipal().equals("anonymousUser")) {
            return ((UserDetail) auth.getDetails()).getNick();
        }
        return "anonymousUser";
    }


    @Autowired
    AuthProvider authenticationProvider;

    @Autowired
    AuthFailHandler failHandler;

    @Override
    public void configure(WebSecurity web) throws Exception {
        String[] strings = new String[]{
                "/style/**", "/icons/**", "/image/**", "/images/**", "/js/**", "/font/**"
        };
        web.ignoring()
//                .antMatchers("/resources/**")
                .antMatchers(strings);

        web.httpFirewall(defaultHttpFirewall());
    }

    @Bean
    public HttpFirewall defaultHttpFirewall() {
        return new DefaultHttpFirewall();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/", "/home").permitAll()
                .antMatchers("/signup").permitAll()
                .and()
                .formLogin()
                .loginPage("/login").permitAll()
                .loginProcessingUrl("/login_processing").permitAll()
                .defaultSuccessUrl("/home", true)
                .failureHandler(failHandler)
//                .failureForwardUrl("/login?error")
                .usernameParameter("userId")
                .passwordParameter("userPassword")
                .permitAll()
                .and()
                .logout()
                .logoutUrl("/logout_processing")
                .logoutSuccessUrl("/home")
                .invalidateHttpSession(true)
                .and().authorizeRequests().anyRequest().authenticated();

        http.authenticationProvider(authenticationProvider);
    }

}
