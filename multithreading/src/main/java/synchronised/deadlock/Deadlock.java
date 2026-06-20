package synchronised.deadlock;

public class Deadlock {

  Object lockA = new Object();
  Object lockB = new Object();

  void usePen() throws InterruptedException {
    Thread.sleep(1000);
    System.out.println(Thread.currentThread().getName() + " trying to acquire lock on LOCK A");
    synchronized (lockA) {
      System.out.println(Thread.currentThread().getName() + " acquired lock on LOCK A");
      System.out.println(Thread.currentThread().getName() + " trying to acquire lock on LOCK B");

      synchronized (lockB) {
        System.out.println(Thread.currentThread().getName() + " acquired lock on LOCK B");
      }
    }
  }

  void usePaper() throws InterruptedException {
    Thread.sleep(1000);
    System.out.println(Thread.currentThread().getName() + " trying to acquire lock on LOCK B");
    synchronized (lockB) {
      System.out.println(Thread.currentThread().getName() + " acquired lock on LOCK B");
      System.out.println(Thread.currentThread().getName() + " trying to acquire lock on LOCK B");
      synchronized (lockA) {
        System.out.println(Thread.currentThread().getName() + " acquired lock on LOCK B");
      }
    }
  }

  public static void main(String[] args) {
    Deadlock deadlock = new Deadlock();
    Runnable usePenAndThenPaper =
        () -> {
          try {
            deadlock.usePen();
          } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException(e);
          }
        };

    Runnable usePaperAndThenPen =
        () -> {
          try {
            deadlock.usePaper();
          } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException(e);
          }
        };

    Thread t1 = new Thread(usePenAndThenPaper, "Thread 1");
    Thread t2 = new Thread(usePaperAndThenPen, "Thread 2");
    t1.start();
    t2.start();
  }
}
