package ru.mirea.maximister.lab2;

import java.util.List;

public class HumanUtilsTest {
    public static void main(String[] args) {
        List<Human> humans = HumanUtils.getHumans(5);
        System.out.println("Исходный список");
        for (var h: humans) System.out.println(h);
        System.out.println();

        humans = HumanUtils.decreaseWeight(humans);
        System.out.println("Задание 1");
        for (var h: humans) System.out.println(h);
        System.out.println();

        humans = HumanUtils.filterByDate(humans);
        System.out.println("Задание 2");
        for (var h: humans) System.out.println(h);
        System.out.println();

        String concatenatedSecondNames = HumanUtils.concatLastNames(humans);
        System.out.println("Задание 3");
        System.out.println(concatenatedSecondNames);
    }
}
