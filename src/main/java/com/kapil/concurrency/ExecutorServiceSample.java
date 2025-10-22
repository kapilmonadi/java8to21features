package com.kapil.concurrency;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ExecutorServiceSample {
    public static void main(String[] args) {
        // creating a simple Executor Service

        System.out.println("I'm the main thread");
        System.out.println(Thread.currentThread().getName());

        // Let's start with a single thread executor
        runSingleThreadExecutor();

        // run multiple threads executor
        runMultipleThreadExecutor();

        // run scheduled thread executor, this will execute after 2 seconds
        runOnceScheduledThreadExecutor();

        // run a task that ius executed at regular intervals
        runFixedRateScheduledTaskExecutor();
    }

    private static void runSingleThreadExecutor() {
        try (var executorService = Executors.newSingleThreadExecutor()) {
            executorService.submit(() -> {
                System.out.println("I'm running in a single thread started by the Executor Service");
                System.out.println("Thread name is : " + Thread.currentThread().getName());
            });
        }
        finally{
            System.out.println("Done using the Executor Service");
        }
    }

    private static void runMultipleThreadExecutor() {
        int threadCount = 2;
        try (var executorService = Executors.newFixedThreadPool(threadCount)) {
            executorService.submit(() -> {
                System.out.println("I'm task 1");
                System.out.println("I'm running in a thread pool executor of 2 threads started by the Executor Service");
                System.out.println("Thread name is : " + Thread.currentThread().getName());
            });
            executorService.submit(() -> {
                System.out.println("I'm task 2");
                System.out.println("I'm running in a thread pool executor of 2 threads started by the Executor Service");
                System.out.println("Thread name is : " + Thread.currentThread().getName());
            });

            executorService.submit(() -> {
                System.out.println("I'm task 3");
                System.out.println("I'm running in a thread pool executor of 2 threads started by the Executor Service");
                System.out.println("Thread name is : " + Thread.currentThread().getName());
            });
        }
        finally{
            System.out.println("Done using the Executor Service");
        }
    }

    private static void runOnceScheduledThreadExecutor() {
        try (var executorService = Executors.newScheduledThreadPool(1)) {
            executorService.schedule(() -> {
                System.out.println("This is a schedules task running");
            }, 2, TimeUnit.SECONDS);
        }
        finally {
            System.out.println("Done using the Executor Service");
        }
    }

    private static void runFixedRateScheduledTaskExecutor() {
    }
}
