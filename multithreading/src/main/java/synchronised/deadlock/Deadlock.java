package synchronised.deadlock;

public class Deadlock {

  Object lockA = new Object();
  Object lockB = new Object();

  void usePen() throws InterruptedException {
    Thread.sleep(50);
    System.out.println(Thread.currentThread().getName() + " trying to acquire lock on LOCK B");
    synchronized (lockA) {
      System.out.println(Thread.currentThread().getName() + " acquired lock on LOCK A");
    }
    usePaper();
  }

  void usePaper() throws InterruptedException {
    Thread.sleep(50);
    System.out.println(Thread.currentThread().getName() + " trying to acquire lock on LOCK B");
    synchronized (lockB) {
      System.out.println(Thread.currentThread().getName() + " acquired lock on LOCK B");
    }
  }

  public static void main(String[] args) {
    Deadlock deadlock = new Deadlock();
    Runnable usePenWithPaper =
        new Runnable() {
          @Override
          public void run() {
            //                deadlock.
          }
        };
  }
}
