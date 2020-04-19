package skhu.sof14.hotthink.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {
    private final ModelMapper modelMapper = new ModelMapper();

    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }
}
