package com.ironbutterfly;

public class Main {
  public static void main(String[] args) {

    WorldWithThreadClass t1 =
        new WorldWithThreadClass(); // NEW => The thread is created in this state
    t1.start(); // Runnable => The thread becomes runnable, ready to run and waiting for CPU time

    // BLOCKING/WAITING STATE => It is in this state when it is waiting for a resource or another
    // thread
    // to perform action

    WorldMain worldInMainThread = new WorldMain();
    worldInMainThread.run();

    WorldWithRunnableInterface worldWithRunnableInterface = new WorldWithRunnableInterface();
    Thread t2 = new Thread(worldWithRunnableInterface);
    t2.start();
    for (; ; ) {
      System.out.println("From Main " + Thread.currentThread().getName());
    }
  }
}
