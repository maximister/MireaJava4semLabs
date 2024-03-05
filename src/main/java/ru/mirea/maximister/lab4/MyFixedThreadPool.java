package ru.mirea.maximister.lab4;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class MyFixedThreadPool implements ExecutorService {

    private final TreadWorker[] treadWorkers;
    private final BlockingQueue<Runnable> tasks;
    private final AtomicBoolean isRunning = new AtomicBoolean();
    private final static String THREADS_AMOUNT_EXCEPTION = "Amount of threads must be at least 1";

    private MyFixedThreadPool(int threads) {
        this.tasks = new LinkedBlockingQueue<>();
        this.treadWorkers = new TreadWorker[threads];
        for (int i = 0; i < threads; i++) {
            treadWorkers[i] = new TreadWorker();
            treadWorkers[i].start();
        }

        this.isRunning.set(true);
    }

    public static MyFixedThreadPool getMyFixedThreadPool(int threads) {
        if (threads < 1) {
            throw new IllegalArgumentException(THREADS_AMOUNT_EXCEPTION);
        }
        return new MyFixedThreadPool(threads);
    }

    @Override
    public void shutdown() {
        isRunning.set(false);
        for (var worker : treadWorkers) {
            try {
                worker.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public List<Runnable> shutdownNow() {
        shutdown();

        List<Runnable> unexecutedTasks = new ArrayList<>();
        tasks.drainTo(unexecutedTasks);

        return unexecutedTasks;
    }

    @Override
    public boolean isShutdown() {
        for (var worker : treadWorkers) {
            if (worker.isAlive()) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean isTerminated() {
        for (var worker : treadWorkers) {
            if (worker.isInterrupted()) {
                return false;
            }
        }

        return true;
    }

    @Override
    public boolean awaitTermination(long timeout, TimeUnit unit) throws InterruptedException {
        long end = System.currentTimeMillis() + unit.toMillis(timeout);
        while (System.currentTimeMillis() < end) {
            if (isTerminated()) {
                return true;
            }
        }
        return false;
    }


    @Override
    public void execute(Runnable command) {
        try {
            tasks.put(Objects.requireNonNull(command));
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private final class TreadWorker extends Thread {
        @Override
        public void run() {
            while (isRunning.get()) {
                Runnable runnable;
                while ((runnable = tasks.poll()) != null) {
                    runnable.run();
                }
            }
        }
    }


    @Override
    public <T> Future<T> submit(Callable<T> task) {
        throw new UnsupportedOperationException();
    }

    @Override
    public <T> Future<T> submit(Runnable task, T result) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Future<?> submit(Runnable task) {
        throw new UnsupportedOperationException();
    }

    @Override
    public <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> tasks) throws InterruptedException {
        throw new UnsupportedOperationException();
    }

    @Override
    public <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> tasks, long timeout, TimeUnit unit) throws InterruptedException {
        throw new UnsupportedOperationException();
    }

    @Override
    public <T> T invokeAny(Collection<? extends Callable<T>> tasks) throws InterruptedException, ExecutionException {
        throw new UnsupportedOperationException();
    }

    @Override
    public <T> T invokeAny(Collection<? extends Callable<T>> tasks, long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
        throw new UnsupportedOperationException();
    }
}
