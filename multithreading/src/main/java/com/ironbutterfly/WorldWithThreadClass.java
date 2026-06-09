package com.ironbutterfly;

public class WorldWithThreadClass extends Thread{
    @Override
    public void run() {
        // RUNNING STATE => when this method if finaly run by CPU
        // this method contains the code that runs in a separate thread
        for(; ;)
            System.out.println("From WorldWithThreadClass " + Thread.currentThread().getName());

        // TERMINATED STATE => when this method execution finishes
    }
}
