package skhu.sof14.hotthink.config.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;
import skhu.sof14.hotthink.config.kafka.ConsumerConfiguration;
import skhu.sof14.hotthink.controller.KafkaController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class LogoutHandler implements LogoutSuccessHandler {
    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
//        ConsumerConfiguration.stopUserTopicConsumeContainer();
//        KafkaController.sseClose();
        response.sendRedirect("/login");
    }
}
