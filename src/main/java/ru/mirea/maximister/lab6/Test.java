package ru.mirea.maximister.lab6;

import ru.mirea.maximister.lab6.abstractFactory.CarService;
import ru.mirea.maximister.lab6.factoryMethod.TaxiOperator;
import ru.mirea.maximister.lab6.prototype.Copyable;

public class Test {
    public static void main(String[] args) {
        //factory method
        TaxiOperator operator = new TaxiOperator();
        operator.takeOrder("Premium");
        operator.takeOrder("Economy");
        System.out.println();

        //abstract factory
        CarService.Order order1 = CarService.makeOrder("Taxi", 4);
        CarService.Order order2 = CarService.makeOrder("Courier", 8);
        order1.process();
        order2.process();

        //builder
        Computer computer = Computer.builder()
                .powerUnit("power unit")
                .cpu("cpu")
                .gpu("gpu")
                .pcCase("case")
                .motherBoard("board")
                .build();

        System.out.println(computer);
        System.out.println();

        //prototype
        Copyable.SomeCopyableObject object = new Copyable.SomeCopyableObject("p1", "p2");
        Copyable c = object.copy();
    }
}
