package com.kta.concurrency;

import java.time.Duration;
import java.util.concurrent.ThreadFactory;

public class VirtualThreadSample {
    public static void main(String[] args) throws InterruptedException {
        runPlatformThread();
        runVirtualThread();
        runVirtualThreadUsingFactory();
        Thread.sleep(Duration.ofSeconds(2));
    }

    private static void runPlatformThread() throws InterruptedException {
        var platformThread = Thread.ofPlatform().unstarted(() -> {
            System.out.println("I'm being executed by a Platform Thread");
            System.out.println(Thread.currentThread().isDaemon());
        });
        platformThread.start();

        Thread.sleep(Duration.ofMillis(500));

        Thread.ofPlatform().start(() -> {
            System.out.println("Running a platform thread created via the Thread.Builder");
            System.out.println(Thread.currentThread().isDaemon());
        });

        Thread.sleep(Duration.ofMillis(500));
    }

    private static void runVirtualThread() throws InterruptedException {
        var virtualThread = Thread.ofVirtual().unstarted(() -> {
            System.out.println("I'm being executed by a Virtual Thread");
            System.out.println(Thread.currentThread().isDaemon());
        });
        virtualThread.start();

        Thread.sleep(Duration.ofMillis(500));

        Thread.ofVirtual().start(() -> {
            System.out.println("Running a virtual thread created via the Thread.Builder");
            System.out.println(Thread.currentThread().isDaemon());
        });

        Thread.sleep(Duration.ofMillis(500));
    }

    private static void runVirtualThreadUsingFactory() throws InterruptedException {
        ThreadFactory threadFactory = Thread.ofVirtual().factory();
        threadFactory.newThread(() -> {
            System.out.println("I'm running in a virtual thread created by the Virtual Thread factory.");
            System.out.println(Thread.currentThread().isDaemon());
        }).start();

        Thread.sleep(Duration.ofMillis(500));
    }
}
