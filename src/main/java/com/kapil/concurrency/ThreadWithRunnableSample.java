package com.kapil.concurrency;

public class ThreadWithRunnableSample implements Runnable {

    @Override
    public void run() {
        System.out.println("[" + Thread.currentThread().getName() + "] Executing thread : " + Thread.currentThread().getName());
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println("[" + Thread.currentThread().getName() + "] Executing thread : " + Thread.currentThread().getName());
        ThreadWithRunnableSample threadWithRunnableSample = new ThreadWithRunnableSample();
        Thread newThread = new Thread(threadWithRunnableSample);
        newThread.start();
        Thread.sleep(2000);
    }
}
