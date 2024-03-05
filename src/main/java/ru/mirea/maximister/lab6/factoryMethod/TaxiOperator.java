package ru.mirea.maximister.lab6.factoryMethod;

import java.util.List;

public class TaxiOperator {
    private List<TaxiCreator> creators = List.of(
            new TaxiCreator.PremiumCreator(),
            new TaxiCreator.EconomyCreator()
    );

    public void takeOrder(String type) {
        TaxiCar car = getCar(type);
        car.takeOrder();
    }

    private TaxiCar getCar(String type) {
        for (var c: creators) {
            if (c.getType().equals(type)) {
                return c.createCar();
            }
        }

        throw new IllegalArgumentException();
    }
}
