package ru.mirea.maximister.lab10;

import org.springframework.stereotype.Component;

@Component("Boxer")
public class Boxer implements Fighter {
    @Override
    public void doFight() {
        System.out.println("Boxer do fight");
    }
}
