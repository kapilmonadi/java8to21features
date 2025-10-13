package com.kapil.concurrency;

import java.time.Duration;

public class ThreadJoinSample {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("[" + Thread.currentThread().getName() + "] Below demonstrates an example of join() in Thread class");
        System.out.println("[" + Thread.currentThread().getName() + "] I'm the main thread");

        // We will first set the isDaemon to false (which is the default behavior
        // of new threads then we will witness the behavior once the daemon flag is set to true
        performChildThreadExecutions(false);
        System.out.println("[" + Thread.currentThread().getName() + "] I'll sleep for 10 seconds before my next task ");
        Thread.sleep(Duration.ofSeconds(10));
        System.out.println("[" + Thread.currentThread().getName() + "] I'm done sleeping, hoping all other threads have completed their execution ");
        performChildThreadExecutions(true);
        System.out.println("[" + Thread.currentThread().getName() + "] Since the child threads are marked as daemon, execution will " +
                "terminate after I (the main thread) is going to complete.");
    }

    private static void performChildThreadExecutions(boolean isDaemon) {

        Thread thread1 = new Thread(() -> {
            System.out.println("[" + Thread.currentThread().getName() + "] I'm Thread 1.");
            System.out.println("[" + Thread.currentThread().getName() + "] I'm going to sleep for atleast 5 seconds");
            try {
                Thread.sleep(Duration.ofSeconds(5));
                System.out.println("[" + Thread.currentThread().getName() + "] I'm done sleeping, enjoyed my nap !");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        thread1.setName("Thread 1");

        Thread thread2 = new Thread(() -> {
            System.out.println("[" + Thread.currentThread().getName() + "] I'm Thread 2.");
            System.out.println("[" + Thread.currentThread().getName() + "] I don';t have anything to do " +
                    "but I need to wait for my good friend Thread 1 to complete, " +
                    "I know it needs a small nap " +
                    "so I'll wait until Thread 1 is done");
            try {
                thread1.join();
                System.out.println("[" + Thread.currentThread().getName() + "] Looks like Thread 1 is done. I'll continue now, bye !!");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        thread2.setName("Thread 2");

        thread1.setDaemon(isDaemon);
        thread2.setDaemon(isDaemon);

        thread1.start();
        thread2.start();

        System.out.println("[" + Thread.currentThread().getName() + "] Thread 1 isDaemon --> " + thread1.isDaemon());
        System.out.println("[" + Thread.currentThread().getName() + "] Thread 2 isDaemon --> " + thread2.isDaemon());
    }
}
