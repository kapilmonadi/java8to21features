package com.kta.concurrency;

import java.time.Duration;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

public class CountDownLatchSample {
    private volatile AtomicInteger duration;

    public static void main(String[] args) throws InterruptedException {
        int threadCount = 5;
        CountDownLatch countDownLatch = new CountDownLatch(threadCount);

        IntStream.range(1, threadCount +1).forEach(value -> {
            Thread.Builder threadBuilder = Thread.ofPlatform().name("Platform Thread - " + value);
            threadBuilder.start(new MyRunnable(countDownLatch, value));
        });

        // Blocking operation
        // wait for all the threads to finish
        //countDownLatch.await();
        boolean await = countDownLatch.await(5, TimeUnit.SECONDS);
        System.out.println("await " + await + " " + countDownLatch.getCount());
        System.out.println("All threads completed");
    }

    private record MyRunnable(CountDownLatch countDownLatch, Integer counter) implements Runnable {

        @Override
            public void run() {
                System.out.println("I'm thread " + Thread.currentThread().getName() + " and counter value is: " + counter);
                try {
                    if (counter == 3) {
                        Thread.sleep(Duration.ofSeconds(10));
                    } else {
                        Thread.sleep(Duration.ofSeconds(2));
                    }
                    countDownLatch.countDown();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
}
