package ru.mirea.maximister.lab6.abstractFactory;

import java.util.List;

public class CarService {
    public static Order makeOrder(String type, int amount) {
        if (type.equals("Taxi")) return new Order(new CarOperator.TaxiOperator(), amount);
        if (type.equals("Courier")) return new Order(new CarOperator.CourierOperator(), amount);

        throw new IllegalArgumentException();
    }

    public static class Order {
        private CarOperator operator;
        private int amount;

        public Order(CarOperator operator, int amount) {
            this.operator = operator;
            this.amount = amount;
        }

        public void process() {
            Car car = amount > 3? operator.getTruck(): operator.getCar();
            car.drive();
        }
    }
}
