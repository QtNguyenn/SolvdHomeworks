package com.solvd.ThreadConnectionPool.ConnectionPool;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;



public class MyConnectionPool {
    private static final Logger logger = LogManager.getLogger("ConnectionPool");
    private BlockingQueue<Connection> connections;
    private int poolSize;

    private volatile boolean isInitialized = false; // Added flag for lazy initialization

    private ExecutorService executorService;
    
    private MyConnectionPool(int poolSize) {
        this.poolSize = poolSize;
        connections = new LinkedBlockingQueue<>(poolSize);
        executorService = Executors.newFixedThreadPool(poolSize);
    }

    private static class ConnectionPoolHolder {
        private static MyConnectionPool INSTANCE;

        private static MyConnectionPool getInstance(int poolSize)
        {
            if (INSTANCE == null)
            {
                synchronized (ConnectionPoolHolder.class)
                {
                    if(INSTANCE == null)
                    {
                        INSTANCE =new MyConnectionPool(poolSize);
                        INSTANCE.initializePool();
                    }
                }
            }
            return INSTANCE;
        }
    }

    public static MyConnectionPool getInstance(int poolSize) {
        return ConnectionPoolHolder.getInstance(poolSize);
    }

    public Connection getConnection() throws InterruptedException {
        initializePool(); // Ensure pool is initialized before returning connection
        return connections.take();
    }

    public void releaseConnection(Connection connection) throws InterruptedException{
        //logger.info("Released connection: " + connection);
        connections.offer(connection);
    }

    public int getSize() {
        return connections.size();
    }

    private void initializePool() {
        if (!isInitialized) { // Check if pool is already initialized
            synchronized (this) {
                if (!isInitialized) {
                    logger.info("Initializing connection pool.");
                    for (int i = 0; i < poolSize; i++) {
                        connections.offer(createConnection());
                    }
                    isInitialized = true; // Set flag to indicate initialization completed
                }
            }
        }
    }

    private Connection createConnection() {
        return new Connection();
    }

    public void shutdown() {
        executorService.shutdown();
    }

  
    
}