package synchronised.interthreadcommunication;

public class InterThreadComm {

  private static final Object lock = new Object();

  private static int data = 0;

  // This flag tells whether there is data available to consume or not.
  private static boolean produced = false;

  static class Producer implements Runnable {

    @Override
    public void run() {
      try {
        produce();
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
        throw new RuntimeException(e);
      }
    }

    void produce() throws InterruptedException {
      for (int i = 1; i <= 10; i++) {

        synchronized (lock) {

          /*
           * If produced == true, it means old data is still not consumed.
           * So producer should wait instead of producing again.
           *
           * We use while, not if, because:
           * 1. Thread can wake up without a real notify, called spurious wakeup.
           * 2. Even after waking up, condition should be checked again.
           */
          while (produced) {
            lock.wait();
          }

          data++;
          produced = true;

          System.out.println(Thread.currentThread().getName() + " produced data -> " + data);

          /*
           * Producer has produced data.
           * Now it notifies waiting threads, mainly the consumer.
           *
           * notifyAll() wakes all threads waiting on this lock.
           * They will still need to reacquire the lock before continuing.
           */
          lock.notifyAll();
        }
      }
    }
  }

  static class Consumer implements Runnable {

    @Override
    public void run() {
      try {
        consume();
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
        throw new RuntimeException(e);
      }
    }

    void consume() throws InterruptedException {
      for (int i = 1; i <= 10; i++) {

        synchronized (lock) {

          /*
           * If produced == false, it means there is no data to consume.
           * So consumer waits until producer creates something.
           *
           * Again, while is important because after waking up,
           * the consumer must re-check whether data is actually available.
           */
          while (!produced) {
            lock.wait();
          }

          System.out.println(Thread.currentThread().getName() + " consumed data -> " + data);

          produced = false;

          /*
           * Consumer has consumed the data.
           * Now producer can produce the next value.
           *
           * So we notify waiting threads, mainly the producer.
           */
          lock.notifyAll();
        }
      }
    }
  }

  public static void main(String[] args) throws InterruptedException {

    Producer producer = new Producer();
    Consumer consumer = new Consumer();

    Thread producerThread = new Thread(producer, "Producer");
    Thread consumerThread = new Thread(consumer, "Consumer");

    producerThread.start();
    consumerThread.start();

    producerThread.join();
    consumerThread.join();

    System.out.println("After execution: " + data);
  }
}
