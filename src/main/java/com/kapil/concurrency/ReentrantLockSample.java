package com.kapil.concurrency;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockSample {
    public static void main(String[] args) {
        Transaction transaction = new Transaction();

        Thread.ofPlatform().name("Debit Thread").start(() -> {
            transaction.debit(50);
        });

        Thread.ofPlatform().name("Debit Thread").start(() -> {
            transaction.credit(300);
        });
    }

    private static class Transaction {
        private final Lock lock = new ReentrantLock();
        private int accBalance = 200;

        {
            System.out.println("Starting balance is : " + accBalance );
        }

        public void debit(int amount){
            lock.lock();
            try{
                accBalance = accBalance - amount;
            }
            finally{
                System.out.println("updated balance after debit is " + accBalance);
                lock.unlock();
            }
        }

        public void credit(int amount){
            lock.lock();
            try {
                accBalance = accBalance + amount;
            }
            finally {
                System.out.println("updated balance after credit is " + accBalance);
                lock.unlock();
            }
        }
    }
}
