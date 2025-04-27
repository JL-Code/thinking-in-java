package org.example.basic.thread;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class CompletableFutureDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            ThreadLogger.log("supplyAsync");
            return "hello";
        });

        ThreadLogger.log("future: " + future.get());
    }
}
