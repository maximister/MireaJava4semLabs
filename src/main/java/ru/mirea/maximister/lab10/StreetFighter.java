package ru.mirea.maximister.lab10;

import org.springframework.stereotype.Component;

@Component("StreetFighter")
public class StreetFighter implements Fighter {
    @Override
    public void doFight() {
        System.out.println("Street fighter do fight");
    }
}
