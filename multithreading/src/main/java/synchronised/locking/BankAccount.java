package synchronised.locking;

// Intrinsic locking => used with synchronised keywords
// Extrinsic locking => explicitly used via java.util.concurrent.locks package

import java.sql.Time;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BankAccount {
    int balance = 300;
    // implicit locking
     synchronized void withdraw(int amount) {
        System.out.println(Thread.currentThread().getName() + " withdrawing " + amount);
        if(balance >= amount) {
            System.out.println(Thread.currentThread().getName() + " proceeding with withdrawal ");
            try {
                Thread.sleep(3000);
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
            balance -= amount;
            System.out.println(Thread.currentThread().getName() + " withrawal done");
        } else {
            System.out.println(Thread.currentThread().getName() + " insufficient balance");
        }
    }

    // explicit locking
    private final Lock lock = new ReentrantLock();

     void withdrawWithLocking(int amount) throws InterruptedException {
         System.out.println(Thread.currentThread().getName() + " attempting to withdraw");
//         lock.tryLock() => for instant check if lock is available or not
         if(lock.tryLock(1000, TimeUnit.MILLISECONDS)) {
             System.out.println(Thread.currentThread().getName() + " acquired the lock");
             // if true
             if(balance >= amount) {
                 try {
                     Thread.sleep(3000);
                     System.out.println(Thread.currentThread().getName() + " proceeding for withdrawal");
                     balance -= amount;
                     System.out.println(Thread.currentThread().getName() + " Completed withdrawal : balance now " + balance);
                 }
                 catch (Exception e) {
                     e.printStackTrace();
                 }
                 finally {
                     lock.unlock();
                 }
             }
             else
                 System.out.println(Thread.currentThread().getName() + " insufficient balance");
         }
         else {
             System.out.println(Thread.currentThread().getName() + " couldnt not aquire the lock");
         }
     }
}
