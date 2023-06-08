package com.solvd.ThreadConnectionPool.Thread;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ThreadMain {
    private static final Logger logger = LogManager.getLogger("Thread");
    public static void main (String[]args){
        Runnable myRunnable1 = new MyRunnable();
        
        Thread thread1 = new Thread(myRunnable1);
        

        thread1.start();

        Thread thread2 = new Thread(() -> {  
            try {
            Thread.sleep(1000);
            } catch (InterruptedException e) {
            throw new RuntimeException(e);
            }
        logger.info("Custom thread (Thread) message " + Thread.currentThread().threadId());

        });
        thread2.start();

        logger.info("Main thread message " + Thread.currentThread().threadId());
    }
}
