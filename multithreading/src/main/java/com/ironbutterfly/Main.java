package com.ironbutterfly;






public class Main {
    public static void main(String[] args) {

        WorldWithThreadClass word = new WorldWithThreadClass();
        word.start();
//        WorldMain worldInMainThread = new WorldMain();
//        worldInMainThread.run();

        WorldWithRunnableInterface worldWithRunnableInterface = new WorldWithRunnableInterface();
        Thread t1 = new Thread(worldWithRunnableInterface);
        t1.start();
        for(;;) {
//            System.out.println("Main");
            System.out.println("From Main " + Thread.currentThread().getName());
        }
    }
}