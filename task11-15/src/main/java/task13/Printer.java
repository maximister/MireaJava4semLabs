package task13;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class Printer {
    @Autowired
    private StudentConfig config;

    @PostConstruct
    public void printStudent() {
        log.info(config.name());
        log.info(config.lastName());
        log.info(config.group());
    }
}
