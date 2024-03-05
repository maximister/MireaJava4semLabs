package ru.mirea.maximister.lab6.factoryMethod;

public interface TaxiCar {
    void takeOrder();

    class EconomyClassTaxi implements TaxiCar {

        @Override
        public void takeOrder() {
            System.out.println("Economy class has taken order");
        }
    }

    class PremiumClassTaxi implements TaxiCar {

        @Override
        public void takeOrder() {
            System.out.println("Premium class has taken order");
        }
    }
}
