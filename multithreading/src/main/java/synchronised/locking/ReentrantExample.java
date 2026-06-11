package synchronised.locking;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantExample {
    private final Lock lock = new ReentrantLock();

    public void outerMethod() {
        lock.lock();
        try {
            System.out.println("Outer method");
            innerMethod();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            lock.unlock();
        }
    }

    public void innerMethod() {
        /// since lock is reentrant lock, NO DEAD LOCK IS CAUSED,
        /// AND HENCE SAME THREAD CAN ACCESS THE LOCK AGAIN
        lock.lock();
        try {
            System.out.println("Inner method");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            lock.unlock();
        }


    }


    public static void main(String[] args) {
        ReentrantExample reentrantExample = new ReentrantExample();
        reentrantExample.outerMethod();
    }
}
