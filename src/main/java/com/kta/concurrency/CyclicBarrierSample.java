package com.kta.concurrency;

import java.time.Duration;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.stream.IntStream;

public class CyclicBarrierSample {
    public static void main(String[] args) {
        int threadCount = 5;
        // barrier action is called once await is called by all threads
        CyclicBarrier cyclicBarrier = new CyclicBarrier(threadCount,
                () -> System.out.println("Barrier action called. All child threads completed !"));

        IntStream.range(1, threadCount +1).forEach(value -> {
            Thread.Builder threadBuilder = Thread.ofPlatform().name("Platform Thread - " + value);
            threadBuilder.start(new MyRunnable(cyclicBarrier, value));
        });
    }

    private record MyRunnable(CyclicBarrier cyclicBarrier, Integer counter) implements Runnable {
        @Override
        public void run() {
            System.out.println("I'm thread " + Thread.currentThread().getName() + " and counter value is: " + counter);
            try {
                // the thread processing counter = 3 will take the longest of 10 secs
                if (counter == 3) {
                    Thread.sleep(Duration.ofSeconds(10));
                } else {
                    Thread.sleep(Duration.ofSeconds(2));
                }
                // the thread is done with its work, call await to block till other threads finish their work
                cyclicBarrier.await();
                System.out.println("Exiting the task, thread name: " + Thread.currentThread().getName() + " and counter value is: " + counter);
            } catch (InterruptedException | BrokenBarrierException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
