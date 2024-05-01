package ru.mirea.maximister.lab7.Proxy;

public class SimpleCalculator implements Calculator {
    @Override
    public int calculate(int first, int second, char operation) {
        return switch (operation) {
            case '+' :
                yield first + second;
            case '-':
                yield first - second;

            case '/':
                yield first / second;

            case '*':
                yield first * second;
            default:
                throw new UnsupportedOperationException();
        };
    }
}

