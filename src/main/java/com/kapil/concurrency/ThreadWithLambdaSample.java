package com.kapil.concurrency;

public class ThreadWithLambdaSample {
    public static void main(String[] args) {
        System.out.println("[" + Thread.currentThread().getName() + "] Executing thread : " + Thread.currentThread().getName());
        Thread newThread = new Thread(() -> {
            System.out.println("[" + Thread.currentThread().getName() + "] Executing thread : " + Thread.currentThread().getName());
        });
        newThread.start();
    }
}
