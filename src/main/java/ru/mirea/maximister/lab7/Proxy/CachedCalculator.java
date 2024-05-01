package ru.mirea.maximister.lab7.Proxy;

import java.util.HashMap;
import java.util.Map;

public class CachedCalculator implements Calculator {
    Calculator calculator = new SimpleCalculator();
    Map<String, Integer> cache = new HashMap<>();

    @Override
    public int calculate(int first, int second, char operation) {
        String key = getKey(first, second, operation);
        if (cache.containsKey(key)) {
            System.out.println("Used cached value");
            return cache.get(key);
        }

        int res = calculator.calculate(first, second, operation);
        System.out.println("Used calculated value");
        cache.put(key, res);
        return res;
    }

    private String getKey(int first, int second, char operation) {
        return Integer.toString(first) + operation + second;
    }
}
