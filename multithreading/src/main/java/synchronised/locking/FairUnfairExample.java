package synchronised.locking;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
/*
* Reentrant lock is an unfair lock by default, which means even if 2 threads are waiting,
* any other thread can barge in and get the entry first
*
* Fair lock is a type of lock in which the first lock in waiting state gets the ock first
* in FIFO mechanism
*
*    ******** NOTE : it does not mean if the locks are started in order they will reach
*                    the is same order, reaching there is managed by OS/JVM,
*                    BUT AFTER REACHING, the order is FIFO
*
*
*        Fair locking also helps to prevent starvation and maintain order
* */
public class FairUnfairExample {
    private final Lock unfairLock = new ReentrantLock();
    // or     private final Lock unfairLock = new ReentrantLock(false);
    private final Lock fairLock = new ReentrantLock(true);

    void unfairLockMethod() {
        unfairLock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + " Unfair locking");
        } catch (Exception e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        } finally {
            unfairLock.unlock();
        }
    }

    void fairLockMethod() {
        fairLock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + " Fair locking ");
        } catch (Exception e) {
            Thread.currentThread().interrupt();
        } finally {
            fairLock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        FairUnfairExample fairUnfairExample = new FairUnfairExample();
        Runnable fair = new Runnable() {
            @Override
            public void run() {
                fairUnfairExample.fairLockMethod();
            }
        };

        Runnable unfair = new Runnable() {
            @Override
            public void run() {
                fairUnfairExample.unfairLockMethod();
            }
        };

        Thread f1 = new Thread(fair);
        Thread f2 = new Thread(fair);
        Thread f3 = new Thread(fair);

        Thread uf1 = new Thread(unfair);
        Thread uf2 = new Thread(unfair);
        Thread uf3 = new Thread(unfair);

        f1.start();
        Thread.sleep(50);

        f2.start();
        Thread.sleep(50);

        f3.start();
        Thread.sleep(50);


        uf1.start();
        Thread.sleep(50);

        uf2.start();
        Thread.sleep(50);

        uf3.start();

    }
}
