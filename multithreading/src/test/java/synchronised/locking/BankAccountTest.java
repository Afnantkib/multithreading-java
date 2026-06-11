package synchronised.locking;

import org.junit.jupiter.api.Test;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BankAccountTest {

    @Test
    public void testWithdrawalSafety() throws InterruptedException {
        BankAccount account = new BankAccount();
        int initialBalance = account.balance; // should be 300
        assertEquals(300, initialBalance);

        // Run two concurrent withdrawals of 100 each
        Thread t1 = new Thread(() -> {
            account.withdraw(100);
        });
        Thread t2 = new Thread(() -> {
            account.withdraw(100);
        });

        t1.start();
        t2.start();
        t1.join();
        t2.join();

        // Since balance was 300, and we withdrew 200 total, remaining balance should be 100
        assertEquals(100, account.balance, "Balance should be 100 after two successful withdrawals");
    }

    @Test
    public void testWithdrawWithLockingTimeout() throws InterruptedException {
        BankAccount account = new BankAccount();
        assertEquals(300, account.balance);

        CountDownLatch firstThreadAcquired = new CountDownLatch(1);
        CountDownLatch testCompleted = new CountDownLatch(1);

        // Thread 1 will acquire the lock and sleep for 3000ms
        Thread t1 = new Thread(() -> {
            try {
                account.withdrawWithLocking(160);
                firstThreadAcquired.countDown();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        // Thread 2 will attempt to acquire the lock but fail due to 1000ms timeout
        Thread t2 = new Thread(() -> {
            try {
                // Wait until Thread 1 starts and acquires the lock
                firstThreadAcquired.await();
                // Sleep briefly to ensure Thread 1 is inside the locked block
                Thread.sleep(100);
                account.withdrawWithLocking(160);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } finally {
                testCompleted.countDown();
            }
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        // Thread 1 should succeed (balance 300 -> 140)
        // Thread 2 should fail to acquire lock (balance remains 140)
        assertEquals(140, account.balance, "Only one withdrawal of 160 should succeed due to locking timeout");
    }
}
