package com.kapil.concurrency;

import java.time.Duration;

public class ThreadJoinExample {

    public static void main(String[] args) {
        System.out.println("Below demonstrates an example of join() in Thread class");
        System.out.println("I'm the main thread");

        Thread thread1 = new Thread(() -> {
            System.out.println("I'm Thread 1.");
            System.out.println("I'm going to sleep for atleast 5 seconds");
            try {
                Thread.sleep(Duration.ofSeconds(5));
                System.out.println("I'm done sleeping, enjoyed my nap !");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        Thread thread2 = new Thread(() -> {
            System.out.println("I'm Thread 2.");
            System.out.println("I don';t have anything to do " +
                    "but I need to wait for my good friend Thread 1 to complete, " +
                    "so I'll wait until Thread 1 is done");
            try {
                thread1.join();
                System.out.println("Looks like Thread 1 is done. I'll continue now, bye !!");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        thread1.start();
        thread2.start();

        System.out.println("Thread 1 isDaemon --> " + thread1.isDaemon());
        System.out.println("Thread 2 isDaemon --> " + thread2.isDaemon());
    }
}
