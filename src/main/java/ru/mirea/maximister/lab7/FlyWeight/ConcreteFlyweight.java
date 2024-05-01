package ru.mirea.maximister.lab7.FlyWeight;

public class ConcreteFlyweight implements Flyweight {
    private Integer intrinsicState;

    public ConcreteFlyweight(Integer intrinsicState) {
        this.intrinsicState = intrinsicState;
    }

    public void operation() {
        System.out.println("Operation with intrinsic state: " + intrinsicState);
    }
}