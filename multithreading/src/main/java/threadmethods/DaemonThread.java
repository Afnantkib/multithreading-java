package threadmethods;

public class DaemonThread extends Thread {
  @Override
  public void run() {
    while (true) {
      System.out.println("Hello");
    }
  }

  public static void main(String[] args) {
    DaemonThread daemonThread = new DaemonThread();
    daemonThread.setDaemon(true); // use this before starting the thread
    daemonThread.start();
    /* now as the thread starts as soon as main completes it will
    not wait for other thread complete its process
    rather as soon as the main or the current threads finishes execution it exits the thread started by it
    */
    /// *************** JVM stays alive only while at least one non daemon thread is running.
    // *****///////
    System.out.println("Main here");
  }
}
