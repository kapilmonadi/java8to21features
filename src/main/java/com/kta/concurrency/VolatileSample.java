package com.kta.concurrency;

import java.time.Duration;

public class VolatileSample {

    private static int data = 0;
    private static boolean isActive = true;

    public static void main(String[] args) throws InterruptedException {
        runThread1();
        Thread.sleep(Duration.ofSeconds(1));
        runThread2();
        Thread.sleep(Duration.ofSeconds(1));
        System.out.println("Main completed !");
    }

    private static void runThread1() {
        Thread.ofPlatform().name("Thread 1").start(() -> {
            int count = 0;
            while(isActive){
                System.out.println(Thread.currentThread().getName() +
                        ": I'll run till isActive is true and print count. Count is " + count++);
            }
            System.out.println("data is " + data);
        });
    }

    private static void runThread2() {
        Thread.ofPlatform().name("Thread 2").start(() -> {
            System.out.println("I will now make isActive as false ");
            data = 42; //assign some value, this will also be visible along with volatile changes
            isActive = false;
            try {
                Thread.sleep(Duration.ofMillis(1));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Done with my task");
        });
    }
}
