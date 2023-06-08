package com.solvd.ThreadConnectionPool.ConnectionPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ConnectionPoolMain {
    private static final Logger logger = LogManager.getLogger("ConnectionPool");
    public static void main (String[]args) throws InterruptedException
    {
        MyConnectionPool pool = MyConnectionPool.getInstance(5);
        logger.info("Step 3: Create Connection Pool." +
        "\nUse collection from java.util.concurrent package."+ 
        "\nConnection class may be mocked. The pool should be threadsafe and lazy initialized.");
        //Acquire connections from multiple threads
        Thread[] acquireThreads = new Thread[5];
        final Connection[] acquiredConnections = new Connection[acquireThreads.length]; // Array to store acquired connections
        for (int i = 0; i < acquireThreads.length; i++) 
        {
            final int index = i; // Create a final index variable to access in the lambda expression
            acquireThreads[i] = new Thread(() -> {
                try {
                    Connection connection = pool.getConnection(); // Wait indefinitely until a connection is available
                    acquiredConnections[index] = connection; // Store the acquired connection in the array
                    logger.info("Acquired connection: " + connection);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            acquireThreads[i].start();
        }

        // Wait for acquire threads to complete
        for (Thread thread : acquireThreads) {
            thread.join();
        }

        // Release connections back to the pool from multiple threads
        Thread[] releaseThreads = new Thread[5];
        for (int i = 0; i < releaseThreads.length; i++) 
        {
            final int index = i;// Create a final index variable to access in the lambda expression
            releaseThreads[i] = new Thread(() -> {
                Connection connection = acquiredConnections[index];
                try {
                    pool.releaseConnection(connection);
                    logger.info("Released connection: " + connection);
                    
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            releaseThreads[i].start();
        }

        // Wait for release threads to complete
        for (Thread thread : releaseThreads) {
            thread.join();
        }

        //step 4
        logger.info("=====================================");
        logger.info("Step 4: Initialize Connection Pool object of size 5."
        +"\nLoad Connection Pool using single threads and Java Thread Pool (7 threads in total)."
        +"\n5 threads should be able to get the connection."+
        "\n2 Threads should wait for the next available connection. The program should wait as well.");
        int poolSize = 5;

        MyConnectionPool pool2 = MyConnectionPool.getInstance(poolSize);
        int totalThreads = 7;
        Thread[] threads = new Thread[totalThreads]; // Array to hold all threads

        // Load Connection Pool using single threads
        for (int i = 0; i < 5; i++) 
        {
            Thread thread = new Thread(() -> {
                try {
                    Connection connection = pool2.getConnection();
                    logger.info("Connection acquired: " + connection);
                    Thread.sleep(5000); //program waits
                    pool2.releaseConnection(connection);
                    logger.info("Connection released: " + connection);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            thread.start();
            threads[i] = thread; // Add the thread to the array
        }

        // Load Connection Pool using Java Thread Pool (2 threads waiting)
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        for (int i = poolSize; i < totalThreads; i++) {
            executorService.submit(() -> {
                try {
                    Connection connection;
                    while (true) 
                    {
                        connection = pool2.getConnection();
                        if (connection != null) {
                            break;
                        }
                        logger.info("Thread waiting for connection...");
                        Thread.sleep(1000);
                    }

                    logger.info("Connection acquired: " + connection);
                    Thread.sleep(1000);
                    pool2.releaseConnection(connection);
                    logger.info("Connection released: " + connection);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }

        executorService.shutdown();

        // Wait for threads to complete their execution
        for (Thread thread : threads) {
            if (thread != null) {
                thread.join();
            }
        }
    }
    
}
