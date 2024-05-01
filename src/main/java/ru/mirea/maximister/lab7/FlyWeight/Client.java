package ru.mirea.maximister.lab7.FlyWeight;

public class Client {
    public static void main(String[] args) {
        FlyweightFactory factory = new FlyweightFactory();

        Flyweight flyweight1 = factory.getFlyweight(1);
        flyweight1.operation();

        Flyweight flyweight2 = factory.getFlyweight(1);
        flyweight2.operation();

        Flyweight flyweight3 = factory.getFlyweight(4);
        flyweight3.operation();

        System.out.println("Number of unique flyweights created: " + factory.getFlyweightsCount());
    }
}
