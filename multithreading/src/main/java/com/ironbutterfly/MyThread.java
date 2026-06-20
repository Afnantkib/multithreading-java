package com.ironbutterfly;

public class MyThread extends Thread {
  @Override
  public void run() {
    System.out.println("RUNNING");
    try {
      Thread.sleep(2000);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
  }

  public static void main(String[] args) throws InterruptedException {
    MyThread t1 = new MyThread(); // NEW
    System.out.println(t1.getState());
    t1.start(); // RUNNABLE
    System.out.println(t1.getState());
    Thread.sleep(100); // this is done to wait for t1 to enter into TIMED_WAITING or sleep mode
    System.out.println(t1.getState());
    t1.join(); // Waits for thread to be TERMINATED
    System.out.println(t1.getState());
  }
}
