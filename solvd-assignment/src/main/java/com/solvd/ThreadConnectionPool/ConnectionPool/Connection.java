package com.solvd.ThreadConnectionPool.ConnectionPool;

public class Connection {
    //private boolean inUse = false;
    //private Thread acquiringThread;

    public void connect() {
        // Mock connection logic
        System.out.println("Connection established");
    }

    public void disconnect() {
        // Mock disconnection logic
        System.out.println("Connection disconnected");
    }

    /*public boolean isInUse() {
        return inUse;
    }

    public void setInUse(boolean inUse) {
        this.inUse = inUse;
    }

    public Thread getAcquiringThread() {
        return acquiringThread;
    }

    public void setAcquiringThread(Thread acquiringThread) {
        this.acquiringThread = acquiringThread;
    }*/
}