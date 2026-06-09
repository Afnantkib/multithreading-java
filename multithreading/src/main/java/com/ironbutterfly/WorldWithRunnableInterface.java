package com.ironbutterfly;

public class WorldWithRunnableInterface implements Runnable{
    @Override
    public void run() {
        // this method contains the code that runs in a separate thread
        for(; ;)
            System.out.println("From WorldWithRunnableInterface " + Thread.currentThread().getName());
    }
}
