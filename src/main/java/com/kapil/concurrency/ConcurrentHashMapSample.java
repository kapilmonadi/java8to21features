package com.kapil.concurrency;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentHashMapSample {
    public static void main(String[] args) {
        // Map to contain the id as key and name as value
        Map<String, String> usersMap = new HashMap<>();
        // simply comment the above line and uncomment the below line to make the operation thread safe
        // Map<String, String> usersMap = new ConcurrentHashMap<>();


        usersMap.put("A123", "Kaps");
        usersMap.put("B456", "John");
        usersMap.put("C789", "Harry");

        // let us create threads to modify the map
        Thread.ofPlatform().name("Thread1").start(() -> {
            for (Map.Entry<String, String> entry : usersMap.entrySet()) {
                System.out.println("Thread 1: " + entry.getKey() + " = " + entry.getValue());
                try {
                    Thread.sleep(Duration.ofMillis(200));
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });

        Thread.ofPlatform().name("Thread2").start(() -> {
            try {
                Thread.sleep(50); // Give thread 1 a head start
                usersMap.put("X987", "Tom"); // Modifying the map during iteration
                System.out.println("Thread 2 added entry {X987 = Tom}");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });
    }
}
