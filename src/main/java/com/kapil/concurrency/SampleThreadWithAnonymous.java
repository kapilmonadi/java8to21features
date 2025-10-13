package com.kapil.concurrency;

public class SampleThreadWithAnonymous {
    public static void main(String[] args) {
        System.out.println("[" + Thread.currentThread().getName() + "] Executing thread : " + Thread.currentThread().getName());

        Thread newThread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("[" + Thread.currentThread().getName() + "] Executing thread : " + Thread.currentThread().getName());
            }
        });
        newThread.start();
    }
}
