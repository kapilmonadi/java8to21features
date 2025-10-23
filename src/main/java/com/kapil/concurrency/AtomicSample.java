package com.kapil.concurrency;

import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

public class AtomicSample {
    private final AtomicInteger count = new AtomicInteger(1);

    // increment and return the latest value
    private void increment(){
        count.incrementAndGet();
        System.out.println("Executed by thread: " + Thread.currentThread().getName());
    }

    private AtomicInteger getCount() {
        return this.count;
    }


    public static void main(String[] args) {
        AtomicSample atomicSample = new AtomicSample();
        int totalCores = Runtime.getRuntime().availableProcessors();
        System.out.println("Total Cores are : "+ totalCores);
        try (var executorService = Executors.newFixedThreadPool(totalCores)) {
            IntStream.range(0, 1000).forEach((element) -> {
                executorService.submit(atomicSample::increment);
            });
        }
        finally {
            System.out.println("Done executing the task");
        }
        System.out.println(" The value of count is : " + atomicSample.getCount());
    }
}
