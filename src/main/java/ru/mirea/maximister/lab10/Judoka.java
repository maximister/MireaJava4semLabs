package ru.mirea.maximister.lab10;

import org.springframework.stereotype.Component;

@Component("Judoka")
public class Judoka implements Fighter {
    @Override
    public void doFight() {
        System.out.println("Judoka do fight");

    }
}
