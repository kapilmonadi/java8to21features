package com.kapil.concurrency;

public class SampleThreadWithRunnable implements Runnable {

    @Override
    public void run() {
        System.out.println("Executing thread : " + Thread.currentThread().getName());
    }

    public static void main(String[] args) {
        System.out.println("Executing thread : " + Thread.currentThread().getName());
        SampleThreadWithRunnable sampleThreadWithRunnable = new SampleThreadWithRunnable();
        Thread newThread = new Thread(sampleThreadWithRunnable);
        newThread.start();
    }
}
