package com.kapil.concurrency;

import java.time.Duration;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.stream.IntStream;

public class CyclicBarrierSample {
    public static void main(String[] args) {
        int threadCount = 5;
        // barrier action is called once await is called by all threads
        CyclicBarrier cyclicBarrier = new CyclicBarrier(threadCount, () -> System.out.println("Barrier action called. All child threads completed"));

        IntStream.range(1, threadCount +1).forEach(value -> {
            Thread.Builder threadBuilder = Thread.ofPlatform().name("Platform Thread - " + value);
            System.out.println("Starting thread counter is :" + value);
            threadBuilder.start(new MyRunnable(cyclicBarrier));
        });


    }

    private static class MyRunnable implements Runnable {

        private final CyclicBarrier cyclicBarrier;
        public MyRunnable(CyclicBarrier cyclicBarrier) {
            this.cyclicBarrier = cyclicBarrier;
        }

        @Override
        public void run() {
            System.out.println("I'm thread " + Thread.currentThread().getName());
            try {
                Thread.sleep(Duration.ofSeconds(2));
                // the thread is done with its work, call await to block till other threads finish their work
                try {
                    cyclicBarrier.await();
                } catch (BrokenBarrierException e) {
                    throw new RuntimeException(e);
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
