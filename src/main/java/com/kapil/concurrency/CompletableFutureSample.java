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

        var completableFutureForDescription = getProductDescriptionWithCompleteCompletableFutureTask();

        // print the value received from the CF, should return the string "No description available"
        // this is a non blocking call so the main thread will continue its execution
        completableFutureForDescription.thenAccept(System.out::println);

        System.out.println("Executing after the call to get the description of the Product");

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

    // The description service is simulated to take at least 5 seconds
    // we create a separate thread that can return the default description i.e. No description available after 2 seconds
    private static CompletableFuture<String> getProductDescriptionWithCompleteCompletableFutureTask() {
        CompletableFuture<String> stringCompletableFuture = CompletableFuture.supplyAsync(() -> {
            // Fetch the description by calling a service,
            // if the service does not return back in stipulated time return a default description
            try {
                Thread.sleep(Duration.ofSeconds(5));
                // simulating that the service call did not return as anticipated and returning default value after 2 mins
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return "Great Brain Teaser";
        });
        Thread.ofPlatform().start(() -> {
            try {
                Thread.sleep(Duration.ofSeconds(2));
                // simulating that the service call did not return and returning default value after 2 mins
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            stringCompletableFuture.complete("No description available");
        });
        return stringCompletableFuture;
    }
}
