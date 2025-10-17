package com.kapil.concurrency;

import java.time.Duration;

public class VirtualThreadSample {
    public static void main(String[] args) throws InterruptedException {
        runPlatformThread();
        runVirtualThread();
        Thread.sleep(Duration.ofSeconds(2));
    }

    private static void runPlatformThread() {
        var platformThread = Thread.ofPlatform().unstarted(() -> {
            System.out.println("I'm bering executed by a Platform Thread");
        });
        platformThread.start();
    }

    private static void runVirtualThread() {
        var platformThread = Thread.ofVirtual().unstarted(() -> {
            System.out.println("I'm bering executed by a Virtual Thread");
        });
        platformThread.start();
    }
}
