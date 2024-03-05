package ru.mirea.maximister.lab6.abstractFactory;

public interface Truck extends Car{
    class TaxiTruck implements Truck {
        @Override
        public void drive() {
            System.out.println("taxiTruck driven");
        }
    }

    class CourierTruck implements Truck {
        @Override
        public void drive() {
            System.out.println("courierTruck driven");
        }
    }
}
