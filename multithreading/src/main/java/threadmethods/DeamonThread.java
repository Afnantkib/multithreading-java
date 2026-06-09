package threadmethods;

public class DeamonThread extends Thread{
    @Override
    public void run() {
        while(true)
            System.out.println("Hello");
    }
    public static void main(String[] args) {
        DeamonThread deamonThread = new DeamonThread();
        deamonThread.setDaemon(true); // use this before starting the thread
        deamonThread.start();
        /* now as the thread starts as soon as main completes it will
        not wait for other thread complete its process
        rather as soon as the main or the current threads finishes execution it exist the thread started by it
        */
        /// *************** JVM stays alive only while at least one non daemon thread is running. *****///////
        System.out.println("Main here");
    }
}
