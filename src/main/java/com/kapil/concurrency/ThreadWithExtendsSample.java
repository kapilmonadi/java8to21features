package com.kapil.concurrency;

public class ThreadWithExtendsSample extends Thread {

    @Override
    public void run() {
        System.out.println("[" + Thread.currentThread().getName() + "] Inside " + Thread.currentThread().getName());
    }

    public static void main(String[] args) {
        System.out.println("[" + Thread.currentThread().getName() + "]  Inside " + Thread.currentThread().getName());
        Thread newThread = new ThreadWithExtendsSample();
        newThread.start();
    }
}
