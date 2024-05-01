package task12.Configuration;

import org.springframework.boot.ApplicationArguments;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class TaskConfig {
    @Bean
    public List<String> args(ApplicationArguments applicationArguments) {
        return applicationArguments.getNonOptionArgs();
    }
}
