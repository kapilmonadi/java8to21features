package com.kapil.concurrency;

public class SampleThreadWithExtends extends Thread {

    @Override
    public void run() {
        System.out.println("Inside " + Thread.currentThread().getName());
    }

    public static void main(String[] args) {
        System.out.println(" Inside " + Thread.currentThread().getName());
        Thread newThread = new SampleThreadWithExtends();
        newThread.start();
    }
}
