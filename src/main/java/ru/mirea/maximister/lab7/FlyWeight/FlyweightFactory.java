package ru.mirea.maximister.lab7.FlyWeight;

import java.util.HashMap;
import java.util.Map;

public class FlyweightFactory {
    private Map<Integer, Flyweight> flyweights = new HashMap<>();

    public Flyweight getFlyweight(Integer key) {
        if (!flyweights.containsKey(key)) {
            flyweights.put(key, new ConcreteFlyweight(key));
        }
        return flyweights.get(key);
    }

    public int getFlyweightsCount() {
        return flyweights.size();
    }
}
