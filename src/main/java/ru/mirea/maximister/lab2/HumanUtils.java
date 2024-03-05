package ru.mirea.maximister.lab2;

import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

/**
 * <p>
 * приложение, которое создает список из объектов класса Human, а
 * затем производит действия в соответствии с вариантом индивидуального
 * задания (список после каждого этапа должен выводиться в консоль.
 * </p>
 * <p>
 * Вариант 6.
 * </p>
 * <p>
 * Уменьшение веса каждого объекта на 5, фильтрация по дате
 * рождения меньшей, чем 3 февраля 1999, конкатенация фамилий в строку через
 * пробел.
 * </p>
 */
public class HumanUtils {
    private static final List<String> FIRST_NAMES;
    private static final List<String> SECOND_NAMES;

    static {
        FIRST_NAMES = List.of(
                "Сергей",
                "Артём",
                "Максим",
                "Иван"
        );

        SECOND_NAMES = List.of(
                "Пономарчук",
                "Власов",
                "Матчин",
                "Коротков"
        );
    }

    public static Human generateHuman() {
        Random random = ThreadLocalRandom.current();
        LocalDate start = LocalDate.of(1970, Month.JANUARY, 1);
        long days = ChronoUnit.DAYS.between(start, LocalDate.now());
        LocalDate randomDate = start.plusDays(random.nextInt((int) days + 1));

        return new Human(
                (int) ChronoUnit.YEARS.between(randomDate, LocalDate.now()),
                FIRST_NAMES.get(random.nextInt(FIRST_NAMES.size())),
                SECOND_NAMES.get(random.nextInt(SECOND_NAMES.size())),
                randomDate,
                40 + random.nextInt(120)
        );
    }

    public static List<Human> getHumans(int size) {
        List<Human> humans = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            humans.add(generateHuman());
        }

        return humans;
    }

    public static List<Human> decreaseWeight(List<Human> humans) {
        return humans.stream()
                .peek(h -> h.setWeight(h.getWeight() > 5? h.getWeight() - 5 : 0))
                .collect(Collectors.toList());
    }

    public static List<Human> filterByDate(List<Human> humans) {
        return humans.stream()
                .filter(h -> h.getBirthDate().isBefore(LocalDate.of(1999, Month.FEBRUARY, 3)))
                .collect(Collectors.toList());
    }

    public static String concatLastNames(List<Human> humans) {
        return humans.stream()
                .map(Human::getLastName)
                .collect( Collectors.joining( " " ) );

    }
}
