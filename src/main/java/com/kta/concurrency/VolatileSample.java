package com.kta.concurrency;

import java.time.Duration;

public class VolatileSample {

    private static volatile boolean isActive = true;

    public static void main(String[] args) throws InterruptedException {
        System.out.println("Starting execution");
        runThread1();
        runThread2();
        Thread.sleep(Duration.ofSeconds(5));
        runThread3();
        Thread.sleep(Duration.ofSeconds(5));
        System.out.println("Main completed !!");
    }

    private static void runThread1() {
        Thread.ofPlatform().name("Thread 1").start(() -> {
            int count = 0;
            while(isActive){
                System.out.println(Thread.currentThread().getName() +
                        ": I'll run till isActive is true and print count. Count is " + count++);
            }
        });
    }

    private static void runThread2() {
        Thread.ofPlatform().name("Thread 2").start(() -> {
            int count = 0;
            while(isActive){
                System.out.println(Thread.currentThread().getName() +
                        ": I'll run till isActive is true and print count. Count is " + count++);
            }
        });
    }

    private static void runThread3() {
        Thread.ofPlatform().name("Thread 3").start(() -> {
            System.out.println(Thread.currentThread().getName() +
                    ": I got a chance to run so I'll make isActive as false to stop Thread 1 and Thread2");
            isActive = false;
            System.out.println(Thread.currentThread().getName() +
                    ": I've made isActive as false, so no statement should be printed by Thread1 and Thread2");
        });
    }
}
