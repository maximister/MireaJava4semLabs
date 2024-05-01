package ru.mirea.maximister.lab7.Proxy;

public class Test {
    public static void main(String[] args) {
        Calculator calculator = new CachedCalculator();

        calculator.calculate(1, 1, '+');
        calculator.calculate(1, 1, '+');
        calculator.calculate(1, 1, '+');
    }
}