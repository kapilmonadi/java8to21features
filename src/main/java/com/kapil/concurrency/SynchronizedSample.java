package com.kapil.concurrency;

public class SynchronizedSample {

    private static String name;
    private static final String LOCK_STR = "lockString";
    public static void main(String[] args) {

        System.out.println("Name is " + name);
        name = "Main";
        System.out.println("Name after assignment in main thread is " + name);
        System.out.println("Creating and calling threads that will modify the name");
        modifyName();
    }

    private static void modifyName() {
        var thread1Str = "Thread1";
        var thread2Str = "Thread2";

        var platformThread1 = Thread.ofPlatform().name(thread1Str).start(() -> {
            System.out.println("I will modify the name to Thread 1");
            updateName(thread1Str);
            updateNameSyncBlock(thread1Str);
            updateNameSyncInstance(thread1Str);
        });
        var platformThread2 = Thread.ofPlatform().name(thread2Str).start(() -> {
            System.out.println("I will modify the name to Thread 2");
            updateName(thread2Str);
            updateNameSyncBlock(thread2Str);
            updateNameSyncInstance(thread2Str);
        });
    }

    // synchronizing on the method
    private synchronized static void updateName(String newName) {
        name = newName;
        System.out.println("The new updated name is " + name + " and it was updated by " + Thread.currentThread().getName());
    }

    // synchronizing on the class object
    private static void updateNameSyncBlock(String newName) {
        synchronized (SynchronizedSample.class){
            name = newName;
            System.out.println("The new updated name is " + name + " and it was updated by " + Thread.currentThread().getName());
        }
    }

    // synchronizing on an object (String object in the current example)
    private static void updateNameSyncInstance(String newName) {
        synchronized (LOCK_STR){
            name = newName;
            System.out.println("The new updated name is " + name + " and it was updated by " + Thread.currentThread().getName());
        }
    }
}
