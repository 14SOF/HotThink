package skhu.sof14.hotthink.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.firewall.DefaultHttpFirewall;
import org.springframework.security.web.firewall.HttpFirewall;
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
                .loginPage("/login")
                .loginProcessingUrl("/login_processing")
                .defaultSuccessUrl("/home", true)
                .failureForwardUrl("/login?error")
                .usernameParameter("userId")
                .passwordParameter("userPassword")
                .permitAll()
                .and().authorizeRequests().anyRequest().authenticated();
//                .and()
//                .authorizeRequests().anyRequest().hasRole("ROLE_USER");
//                .and()
//                .authorizeRequests().anyRequest().authenticated();



//                .successHandler(new AuthenticationSuccessHandler() {
//                    @Override
//                    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
//                                                        Authentication authentication) throws IOException, ServletException {
//                        System.out.println("성공"+" "+request.getMethod()+" "+request.getPathInfo());
//                        redirectStrategy.sendRedirect(request, response, "/home");
//                    }
//                })

        http.authenticationProvider(authenticationProvider);
    }

}
