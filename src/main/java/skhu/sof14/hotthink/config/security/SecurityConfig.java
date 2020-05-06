package skhu.sof14.hotthink.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.firewall.DefaultHttpFirewall;
import org.springframework.security.web.firewall.HttpFirewall;
import skhu.sof14.hotthink.service.AuthProvider;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    AuthProvider authenticationProvider;

    @Autowired
    AuthFailHandler failHandler;

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/v2/api-docs",
                "/configuration/ui",
                "/swagger-resources/**",
                "/configuration/security",
                "/swagger-ui.html",
                "/webjars/**");

        String[] strings = new String[]{
                "/style/**", "/icons/**", "/image/**", "/images/**", "/js/**", "/font/**"
        };
        web.ignoring()
//                .antMatchers("/resources/**")
                .antMatchers(strings);

        web.httpFirewall(defaultHttpFirewall()); // 더블슬래쉬 허용
    }

    @Bean
    public HttpFirewall defaultHttpFirewall() {
        return new DefaultHttpFirewall();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable()
                .httpBasic().disable()
                .authorizeRequests()
                .antMatchers("/", "/home").permitAll()
                .antMatchers("/create/user").permitAll()
                .antMatchers("/check/**").permitAll()
                .antMatchers("/read/**").hasRole("USER")
                .antMatchers("/create/**").hasRole("USER")
                .antMatchers("/delete/**").hasRole("USER")
                .antMatchers("/update/**").hasRole("USER")
                //임시
                .antMatchers("/user_delete").permitAll()
                .antMatchers("/deleteCk").permitAll()
                .antMatchers("/delete/**").permitAll()
                .antMatchers("list/**").permitAll()
                .antMatchers("/testCharge").permitAll()
                //임시끝
                .and()
                .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/home", true)
                .failureHandler(failHandler)
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
