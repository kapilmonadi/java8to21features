package com.kapil.concurrency;

import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

public class AtomicSample {
    private final AtomicInteger count = new AtomicInteger(1);
    private int nonThreadSafeCount = 1;

    // increment and return the latest value
    private void increment(){
        count.incrementAndGet();
        nonThreadSafeCount++;
        System.out.println("Executed by thread: " + Thread.currentThread().getName());
    }

    private AtomicInteger getCount() {
        return this.count;
    }

    private int getNonThreadSafeCount() {
        return this.nonThreadSafeCount;
    }

    public static void main(String[] args) {
        AtomicSample atomicSample = new AtomicSample();
        int totalCores = Runtime.getRuntime().availableProcessors();
        System.out.println("Total Cores are : "+ totalCores);
        try (var executorService = Executors.newFixedThreadPool(1)) {
            IntStream.range(1, 1000000).forEach((element) -> {
                executorService.submit(atomicSample::increment);
            });
        }
        finally {
            System.out.println("Done executing the task");
        }
        System.out.println(" The value of count is : " + atomicSample.getCount());
        System.out.println(" The value fo non-thread safe count is " + atomicSample.getNonThreadSafeCount());
    }
}
