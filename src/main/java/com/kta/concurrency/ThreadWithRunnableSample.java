package com.kta.concurrency;

public class ThreadWithRunnableSample implements Runnable {

    @Override
    public void run() {
        // business logic that we want a thread to execute goes in here
        System.out.println("[" + Thread.currentThread().getName() + "] Executing thread : " + Thread.currentThread().getName());
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println("[" + Thread.currentThread().getName() + "] Executing thread : " + Thread.currentThread().getName());
        ThreadWithRunnableSample threadWithRunnableSample = new ThreadWithRunnableSample();
        Thread newThread = new Thread(threadWithRunnableSample);
        newThread.setName("New Thread");
        newThread.start();
        Thread.sleep(2000);
    }
}
