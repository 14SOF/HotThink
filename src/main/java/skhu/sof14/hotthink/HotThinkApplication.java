package skhu.sof14.hotthink;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class HotThinkApplication {

    public static void main(String[] args) {
        SpringApplication.run(HotThinkApplication.class, args);
    }

}
