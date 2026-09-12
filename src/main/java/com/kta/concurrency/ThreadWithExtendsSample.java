package com.kta.concurrency;

public class ThreadWithExtendsSample extends Thread {

    @Override
    public void run() {
        System.out.println("[" + Thread.currentThread().getName() + "] Inside " + Thread.currentThread().getName());
    }

    public static void main(String[] args) {
        System.out.println("[" + Thread.currentThread().getName() + "]  Inside " + Thread.currentThread().getName());
        Thread newThread = new ThreadWithExtendsSample();
        newThread.setName("Custom App Thread");
        newThread.start();
    }
}
