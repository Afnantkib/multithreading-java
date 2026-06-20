package synchronised.readwritelock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import synchronised.Counter;

/**
 * If no lock is held: read lock can acquire ✅ write lock can acquire ✅
 *
 * <p>If read lock is held: another read lock can acquire ✅ write lock cannot acquire ❌ waits
 *
 * <p>If write lock is held: read lock cannot acquire ❌ waits another write lock cannot acquire ❌
 * waits *
 */
public class ReadAndWriteLock {
  private Counter counter;

  private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

  private Lock readlock = readWriteLock.readLock();

  private Lock writelock = readWriteLock.writeLock();

  ReadAndWriteLock(Counter counter) {
    this.counter = counter;
  }

  void increment() throws InterruptedException {

    Thread.sleep(50);
    writelock.lock();
    try {
      System.out.println(Thread.currentThread().getName() + " writing");
      counter.increment();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      writelock.unlock();
    }
  }

  void read() throws InterruptedException {
    Thread.sleep(50);
    readlock.lock();
    // Multiple threads can acquire this read lock
    // But it can only be aquired when there no acquired write lock
    try {
      System.out.println(
          Thread.currentThread().getName() + " reading count -> " + counter.getValue());
    } finally {
      readlock.unlock();
    }
  }

  public static void main(String[] args) {
    Counter counter = new Counter();
    ReadAndWriteLock readAndWriteLock = new ReadAndWriteLock(counter);
    Runnable readTask =
        new Runnable() {
          @Override
          public void run() {
            for (int i = 0; i < 10; i++) {
              try {
                readAndWriteLock.read();
              } catch (InterruptedException e) {
                throw new RuntimeException(e);
              }
            }
          }
        };

    Runnable writeTask =
        new Runnable() {
          @Override
          public void run() {
            for (int i = 0; i < 10; i++) {
              try {
                readAndWriteLock.increment();
              } catch (InterruptedException e) {
                throw new RuntimeException(e);
              }
            }
          }
        };

    Thread readthread1 = new Thread(readTask);
    Thread readthread2 = new Thread(readTask);
    Thread writetask = new Thread(writeTask);

    writetask.start();
    readthread1.start();
    readthread2.start();
  }
}
