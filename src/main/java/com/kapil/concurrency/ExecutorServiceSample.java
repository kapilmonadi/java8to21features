package com.kapil.concurrency;

import java.util.concurrent.Executors;

public class ExecutorServiceSample {
    public static void main(String[] args) {
        // creating a simple Executor Service

        // Let's start with a single thread executor

        System.out.println("I'm the main thread");
        System.out.println(Thread.currentThread().getName());

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
}
