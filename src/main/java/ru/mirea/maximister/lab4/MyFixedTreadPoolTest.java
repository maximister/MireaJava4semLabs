package ru.mirea.maximister.lab4;

import java.util.concurrent.ExecutorService;

public class MyFixedTreadPoolTest {
    public static void main(String[] args) {
        ExecutorService executorService = MyFixedThreadPool.getMyFixedThreadPool(3);

        executorService.execute(() -> {
            try {
                System.out.println("task 1 was started at " + System.currentTimeMillis());
                Thread.sleep(500);
                System.out.println("task 1 was completed");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        executorService.execute(() -> {
            try {
                System.out.println("task 2 was started at " + System.currentTimeMillis());
                Thread.sleep(100);
                System.out.println("task 2 was completed");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        executorService.execute(() -> {
            try {
                System.out.println("task 3 was started at " + System.currentTimeMillis());
                Thread.sleep(100);
                System.out.println("task 3 was completed");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        executorService.execute(() -> {
            try {
                System.out.println("task 4 was started at " + System.currentTimeMillis());
                Thread.sleep(100);
                System.out.println("task 4 was completed");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        executorService.shutdown();
    }
}
