package com.solvd.ThreadConnectionPool.Thread;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class MyRunnable implements Runnable{
    private static final Logger logger = LogManager.getLogger("Thread");
    @Override
    public void run(){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        logger.info("Custom (runnable) thread message " + Thread.currentThread().threadId());
    }
}
