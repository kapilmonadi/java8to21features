package com.kapil.concurrency;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class CompletableFutureSample {

    private static String productId = "ABC123";

    public static void main(String[] args) throws InterruptedException, ExecutionException {

        System.out.println("About to run an async task, sending for execution");
        CompletableFuture<Void> asyncTaskCF = runAsyncTask();
        // Blocking call similar ot Future.get()
        // if below line is commented then main thread will continue executing without being blocked
        asyncTaskCF.get();
        System.out.println("Async task completed");

        var completableFutureProductDetails = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(Duration.ofSeconds(1));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return fetchProductDetails(productId);
        });

        var completableFutureProductReviews = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(Duration.ofSeconds(2));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return fetchProductReviews(productId);
        });

        CompletableFuture<Void> finalResultCompletableFuture = CompletableFuture.allOf(completableFutureProductDetails, completableFutureProductReviews);
        finalResultCompletableFuture.thenRun(() -> {
            try {
                System.out.println("Product Details : " + completableFutureProductDetails.get());
                System.out.println("Product Reviews : " + completableFutureProductReviews.get());
            } catch (InterruptedException | ExecutionException e) {
                throw new RuntimeException(e);
            }
        });
        System.out.println("Sleeping for 5 seconds so background threads can complete");
        Thread.sleep(Duration.ofSeconds(5));
        System.out.println("Main completed");
    }


    // return the Product based on Product Id
    private static Product fetchProductDetails(String productId){
        return new Product("ABC123", 450.0, "Rubik's Cube");
    }

    private static List<String> fetchProductReviews(String productId){
        return List.of("Good product, I'm happy", "Loved it");
    }

    private static record Product(String productId, Double productPrice, String productDescription) {
    }


    private static CompletableFuture<Void> runAsyncTask(){
        return CompletableFuture.runAsync(() -> {
            try {
                Thread.sleep(Duration.ofSeconds(1));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Task completed !");
        });
    }
}
