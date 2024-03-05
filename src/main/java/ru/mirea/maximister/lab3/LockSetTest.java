package ru.mirea.maximister.lab3;

import java.util.HashSet;

public class LockSetTest {
    public static void main(String[] args) {
        LockSet<Integer> lockSet = new LockSet<>(new HashSet<>());

        Thread writeThread = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                lockSet.add(i);
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
                lockSet.remove(0);
                System.out.println("Удален 1 элемент");
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread readThread = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                lockSet.contains(i);
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
