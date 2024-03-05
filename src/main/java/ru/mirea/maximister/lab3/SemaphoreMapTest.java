package ru.mirea.maximister.lab3;

import java.util.HashMap;
import java.util.Map;

public class SemaphoreMapTest {
    public static void main(String[] args) {
        Map<String, Integer> map = new SemaphoreMap<>(new HashMap<>());

        Thread writeThread = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                map.put("Key" + i, i);
                System.out.println("Добавлен элемент: " + i);
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread deleteThread = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                map.remove("Key" + i);
                System.out.println("Удален элемент " + i);
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread readThread = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                map.get("Key" + i);
                System.out.println("Осуществляется чтение");
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        writeThread.start();
        deleteThread.start();
        readThread.start();

        try {
            writeThread.join();
            deleteThread.join();
            readThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
