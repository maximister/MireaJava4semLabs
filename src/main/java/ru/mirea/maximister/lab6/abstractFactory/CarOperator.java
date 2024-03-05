package ru.mirea.maximister.lab6.abstractFactory;

public interface CarOperator {
    Truck getTruck();
    PassengerCar getCar();

    public class TaxiOperator implements CarOperator {

        @Override
        public Truck getTruck() {
            return new Truck.TaxiTruck();
        }

        @Override
        public PassengerCar getCar() {
            return new PassengerCar.TaxiCar();
        }
    }

    public class CourierOperator implements CarOperator {

        @Override
        public Truck getTruck() {
            return new Truck.CourierTruck();
        }

        @Override
        public PassengerCar getCar() {
            return new PassengerCar.CourierCar();
        }
    }
}
