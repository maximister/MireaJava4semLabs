package ru.mirea.maximister.lab6.abstractFactory;

public interface PassengerCar extends Car {

    class TaxiCar implements PassengerCar {
        @Override
        public void drive() {
            System.out.println("taxiPassengerCar driven");
        }
    }

    class CourierCar implements PassengerCar {
        @Override
        public void drive() {
            System.out.println("courierPassengerCar driven");
        }
    }
}
