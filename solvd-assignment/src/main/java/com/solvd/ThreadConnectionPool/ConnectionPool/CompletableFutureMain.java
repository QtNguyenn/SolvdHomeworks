package com.solvd.ThreadConnectionPool.ConnectionPool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class CompletableFutureMain {
    private static final Logger logger = LogManager.getLogger("ConnectionPool");
    public static void main (String[] args) throws InterruptedException
    {
        
        int poolSize = 5;
        int waitingConnections = 2;
        logger.info("=================================");
        logger.info("Implement previous point but with interfaces Future and CompletableStage.");
        ExecutorService executorService = Executors.newFixedThreadPool(7);
        MyConnectionPool pool = MyConnectionPool.getInstance(poolSize);
        // Acquire connections asynchronously using CompletableFuture and store in a list
        List<CompletableFuture<Connection>> futures = new ArrayList<>();

        for (int i = 0; i < 5; i++) 
        {
            CompletableFuture<Connection> future = CompletableFuture.supplyAsync(() -> {
                try 
                {
                    return pool.getConnection();
                } catch (InterruptedException e) 
                {
                    throw new CompletionException(e);
                }
            }, executorService);
            futures.add(future);
        }

        // Wait for all CompletableFuture objects to complete
        CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).join();

        // Process the connections from the list
        for (int i = 0; i < 5; i++) {
            CompletableFuture<Connection> future = futures.get(i);
            int index = i + 1;
            future.thenAcceptAsync(connection -> {
                logger.info("Connection acquired:" + index + ": " + connection);
                try {
                    Thread.sleep(2000);
                    pool.releaseConnection(connection);
                } catch (InterruptedException e) 
                {
                    e.printStackTrace();
                }
                logger.info("Connection released " + index + ": " + connection);
            }, executorService);
        }

        // Acquire waiting connections using CompletableFuture
        List<CompletableFuture<Connection>> waitingFutures = new ArrayList<>();

        for (int i = 0; i < waitingConnections; i++) {
            CompletableFuture<Connection> future = CompletableFuture.supplyAsync(() -> {
                try {
                    return pool.getConnection();
                } catch (InterruptedException e) {
                    throw new CompletionException(e);
                }
            }, executorService);
            waitingFutures.add(future);
        }

        // Wait for all waiting CompletableFuture objects to complete
        CompletableFuture.allOf(waitingFutures.toArray(new CompletableFuture[0])).join();

        // Process the waiting connections from the list
        for (int i = 0; i < waitingConnections; i++) 
        {
            CompletableFuture<Connection> future = waitingFutures.get(i);
            int index = i + 1;
            future.thenAcceptAsync(connection -> {
                logger.info("Waiting Connection acquired " + index + ": " + connection);
                
                try {
                    Thread.sleep(1000);
                    pool.releaseConnection(connection);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                logger.info("Waiting Connection released " + index + ": " + connection);
            }, executorService);
        }

        // Wait for all the connections to be released
        Thread.sleep(2000);

        // Shut down the connection pool and executor service
        pool.shutdown();
        executorService.shutdown();

        logger.info("Testing completed.");


    }
}
