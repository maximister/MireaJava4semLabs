package ru.mirea.maximister.lab10;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FighterConfig {
    @Bean
    public Fighter StreetFighter() {
        return new StreetFighter();
    }

    @Bean
    public Fighter Boxer() {
        return new Boxer();
    }

    @Bean
    public Fighter Judoka() {
        return new Judoka();
    }
}
