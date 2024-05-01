package ru.mirea.maximister.lab1;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


public class StudentScoreComparator implements Comparator<Student> {
    @Override
    public int compare(Student o1, Student o2) {
        return Integer.compare(o1.score(), o2.score());
    }

    public static void main(String[] args) {
        List<Student> students = new ArrayList<>(List.of(
                new Student("Шапошникова Ясмина Данииловна", 85),
                new Student("Комаров Марк Ильич", 44),
                new Student("Попова Юлия Тимуровна", 76),
                new Student("Владимирова Варвара Данииловна", 11),
                new Student("Грачева Екатерина Владимировна", 98),
                new Student("Александрова Анна Андреевна", 26),
                new Student("Скворцова Мария Савельевна", 58),
                new Student("Кузнецов Егор Степанович", 31),
                new Student("Воронцов Давид Платонович", 17)
        ));

        students.sort(new StudentScoreComparator());
        for (var s : students) System.out.println(s);
    }
}
