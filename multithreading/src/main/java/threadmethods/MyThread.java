package threadmethods;

public class MyThread extends Thread {

  MyThread(String name) {
    super(name);
  }

  @Override
  public void run() {
    for (int i = 0; i < 5; i++) {
      System.out.println(Thread.currentThread().getName() + " - " + i);
      Thread.yield(); // this gives a hint to the scheduler to give chance to other threads
    }
  }

  public static void main(String[] args) throws InterruptedException {
    MyThread l = new MyThread("Low Priority");
    l.setPriority(Thread.MIN_PRIORITY);
    MyThread m = new MyThread("Medium Priority");
    m.setPriority(Thread.NORM_PRIORITY);
    MyThread h = new MyThread("High Priority");
    h.setPriority(Thread.MAX_PRIORITY);
    //        l.start();
    //        m.start();
    //        h.start();

    MyThread t1 = new MyThread("T1");
    MyThread t2 = new MyThread("T2");
    t1.start();
    t2.start();

    l.interrupt(); // this interrupts whatever the thread state is
  }
}

// start , run, sleep, join, setpriority, interrupt, yeild, setdeamon

// User threads are the ones that we create and manage
// Deamon threads are the background threads that manage things like garbage collector, etc
// JVM waits for user thread but dosent wait for deamon threads to exit the process

// without yield
// T1 - 0
// T1 - 1
// T1 - 2
// T1 - 3
// T1 - 4
// T2 - 0
// T2 - 1
// T2 - 2
// T2 - 3
// T2 - 4

// With yield T2 gets more chance as T1
// T2 - 0
// T1 - 0
// T2 - 1
// T2 - 2
// T1 - 1
// T1 - 2
// T2 - 3
// T1 - 3
// T1 - 4
// T2 - 4
