package synchronised.locking;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class FairUnfairExample {
    private final Lock unfairLock = new ReentrantLock();
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

    public static void main(String[] args) {
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
        f2.start();
        f3.start();

        uf1.start();
        uf2.start();
        uf3.start();
    }
}
