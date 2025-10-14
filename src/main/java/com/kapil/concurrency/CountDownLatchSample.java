package com.kapil.concurrency;

import java.time.Duration;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

public class CountDownLatchSample {
    private volatile AtomicInteger duration;

    public static void main(String[] args) throws InterruptedException {
        int threadCount = 5;
        CountDownLatch countDownLatch = new CountDownLatch(threadCount);

        IntStream.range(1, threadCount +1).forEach(value -> {
            Thread.Builder threadBuilder = Thread.ofPlatform().name("Platform Thread - " + value);
            System.out.println("Starting thread counter is :" + value);
            threadBuilder.start(new MyRunnable(countDownLatch));
        });

        // Blocking operation
        // wait for all the threads to finish
        countDownLatch.await();
        System.out.println("All threads completed");
    }

    private static class MyRunnable implements Runnable {

        private final CountDownLatch countDownLatch;

        private MyRunnable(CountDownLatch countDownLatch) {
            this.countDownLatch = countDownLatch;
        }

        @Override
        public void run() {
            System.out.println("I'm thread " + Thread.currentThread().getName());
            try {
                Thread.sleep(Duration.ofSeconds(2));
                countDownLatch.countDown();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
