package com.kapil.concurrency;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentHashMapSample {
    public static void main(String[] args) {
        // Map to contain the id as key and name as value
        Map<String, String> usersMap = new ConcurrentHashMap<>();

        usersMap.put("A123", "Kaps");
        usersMap.put("B456", "John");
        usersMap.put("C789", "Harry");

        // let us create threads to modify the map
        Thread.ofPlatform().start(() -> {
           // update the usersMap
        });

        Thread.ofPlatform().start(() -> {
            // update the usersMap
        });
    }
}
