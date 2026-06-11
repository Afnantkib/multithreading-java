package synchronised.locking;




public class Main {


    public static void main(String[] args) throws InterruptedException {
        BankAccount bankAccount = new BankAccount();
        Runnable task = new Runnable() {
            @Override
            public void run() {
//                bankAccount.withdraw(160);
                try {
                    bankAccount.withdrawWithLocking(160);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        };
        Thread thread1 = new Thread(task, "Thread 1");
        Thread thread2 = new Thread(task, "Thread 2");
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();

        System.out.println("Final balance " + bankAccount.balance);
    }
}
