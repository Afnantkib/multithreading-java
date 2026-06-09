package com.ironbutterfly;

public class WorldWithThreadClass extends Thread{
    @Override
    public void run() {
        // this method contains the code that runs in a separate thread
        for(; ;)
            System.out.println("From WorldWithThreadClass " + Thread.currentThread().getName());
    }
}
