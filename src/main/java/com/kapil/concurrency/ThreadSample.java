package com.kapil.concurrency;

public class ThreadSample {
    public static void main(String[] args) throws InterruptedException {
        // Print the currently executing Thread's name
        printCurrentThreadName();
        createSimpleThread();
        Thread.sleep(2000);
    }

    private static void createSimpleThread() {
        Thread thread = new Thread(() -> {
            System.out.println("[" + Thread.currentThread().getName() + "]  I'm " + Thread.currentThread().getName());
            System.out.println("[" + Thread.currentThread().getName() + "]  I simply execute code. I do not return anything ");
            System.out.println("[" + Thread.currentThread().getName() + "]  I'm a Runnable");
            printCurrentThreadName();
        });
        thread.setName("A simple thread");
        thread.start();
    }


    private static void printCurrentThreadName() {
        System.out.println("[" + Thread.currentThread().getName() + "] The current thread name is " + Thread.currentThread().getName());
    }
}
