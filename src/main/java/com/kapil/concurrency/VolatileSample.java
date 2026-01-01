package com.kapil.concurrency;

import java.time.Duration;

public class VolatileSample {

    private static volatile boolean isActive = true;

    public static void main(String[] args) throws InterruptedException {
        runThread1();
        // Sleep main thread for few so it gets blocked
        Thread.sleep(Duration.ofSeconds(5));
        runThread2();
        System.out.println("Main completed !!");
    }

    private static void runThread1(){
        Thread.ofPlatform().start(() -> {
            int count = 0;
            while(isActive){
                System.out.println("I'll run till isActive is true and print count. Count is " + count++);
            }
        });
    }

    private static void runThread2(){
        Thread.ofPlatform().start(() -> {
            System.out.println("I got a chance to run so I'll make isActive as false");
            isActive = false;
            System.out.println("I've made isActive as false, now no statement should be printed by Thread1 as isActive is volatile");
        });
    }
}
