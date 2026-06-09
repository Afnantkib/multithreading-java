package com.ironbutterfly;

public class WorldMain {

    void run() {
        // here no separate thread is created and thus runs in main thread
        while(true)
            System.out.println("From WorldMain" + Thread.currentThread().getName());
    }
}

