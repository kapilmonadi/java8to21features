package com.kapil.concurrency;

import java.time.Duration;
import java.util.concurrent.ThreadFactory;

public class VirtualThreadSample {
    public static void main(String[] args) throws InterruptedException {
        runPlatformThread();
        runVirtualThread();
        runVirtualThreadUsingFactory();
        Thread.sleep(Duration.ofSeconds(2));
    }

    private static void runPlatformThread() {
        var platformThread = Thread.ofPlatform().unstarted(() -> {
            System.out.println("I'm bering executed by a Platform Thread");
        });
        platformThread.start();
    }

    private static void runVirtualThread() {
        var virtualThread = Thread.ofVirtual().unstarted(() -> {
            System.out.println("I'm bering executed by a Virtual Thread");
        });
        virtualThread.start();
    }

    private static void runVirtualThreadUsingFactory() {
        ThreadFactory threadFactory = Thread.ofVirtual().factory();
        threadFactory.newThread(() -> {
            System.out.println("I'm running in a virtual thread created by the Virtual Thread factory.");
        }).start();
    }
}
