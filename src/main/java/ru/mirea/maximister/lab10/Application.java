package ru.mirea.maximister.lab10;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class Application {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(FighterConfig.class);

        Fighter streetFighter = context.getBean("StreetFighter", Fighter.class);
        streetFighter.doFight();
        Fighter boxer = context.getBean("Boxer", Fighter.class);
        boxer.doFight();
        Fighter judoka = context.getBean("Judoka", Fighter.class);
        judoka.doFight();
    }
}
