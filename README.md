# Java Multithreading

A simple reference repository demonstrating core Java multithreading and concurrency concepts.

---

## 📂 Project Structure

*   📂 **[`com.ironbutterfly`](./multithreading/src/main/java/com/ironbutterfly)** — Thread creation and lifecycle state monitoring.
*   📂 **[`threadvsrunnable`](./multithreading/src/main/java/threadvsrunnable)** — Extending `Thread` vs. implementing `Runnable`.
*   📂 **[`threadmethods`](./multithreading/src/main/java/threadmethods)** — Thread controls (`yield()`, `sleep()`, `join()`, `interrupt()`), priorities, and Daemon threads.
*   📂 **[`synchronised`](./multithreading/src/main/java/synchronised)** — Thread-safe counters using synchronized methods and blocks.
*   📂 **[`synchronised.locking`](./multithreading/src/main/java/synchronised/locking)** — Explicit locks (`ReentrantLock`), fair/unfair locks, timed lock acquisition, and reentrancy.

---

## 💡 Key Concurrency Concepts

### 1. Thread Creation & States
*   **Creation**: Implement `Runnable` ([WorldWithRunnableInterface.java](./multithreading/src/main/java/com/ironbutterfly/WorldWithRunnableInterface.java)) or extend `Thread` ([WorldWithThreadClass.java](./multithreading/src/main/java/com/ironbutterfly/WorldWithThreadClass.java)). Use `Runnable` if your class already extends another class ([A.java](./multithreading/src/main/java/threadvsrunnable/A.java)).
*   **States**: Monitor state transitions (`NEW`, `RUNNABLE`, `TIMED_WAITING`, `TERMINATED`) in [MyThread.java](./multithreading/src/main/java/com/ironbutterfly/MyThread.java).

### 2. Thread Controls & Daemon Threads
*   **Methods**: [MyThread.java](./multithreading/src/main/java/threadmethods/MyThread.java) demonstrates scheduling tools like `yield()`, priorities, and thread interruption.
*   **Daemon Threads**: Background threads that stop as soon as all user threads finish executing ([DeamonThread.java](./multithreading/src/main/java/threadmethods/DeamonThread.java)).

### 3. Synchronization & Locks
*   **Synchronized**: Use method level or block level `synchronized` modifiers to protect shared variables from race conditions ([Counter.java](./multithreading/src/main/java/synchronised/Counter.java)).
*   **Explicit Locks (`ReentrantLock`)**:
    *   **Timed Lock**: Try to acquire a lock with a timeout to avoid deadlock ([BankAccount.java](./multithreading/src/main/java/synchronised/locking/BankAccount.java)).
    *   **Fairness**: Order lock acquisition to prevent thread starvation ([FairUnfairExmaple.java](./multithreading/src/main/java/synchronised/locking/FairUnfairExmaple.java)).
    *   **Reentrancy**: Allow a thread to re-acquire a lock it already holds ([ReentrantExample.java](./multithreading/src/main/java/synchronised/locking/ReentrantExample.java)).

---

## 🛠️ Build & Run

### Prerequisites
*   Java Development Kit (JDK) 24+
*   Apache Maven

### Build Command
```bash
mvn clean package
```

### Execution Examples
Run any demonstration class from the `multithreading` directory:
```bash
# Thread States
mvn exec:java -Dexec.mainClass="com.ironbutterfly.MyThread"

# Synchronized Counter
mvn exec:java -Dexec.mainClass="synchronised.MyThread"

# ReentrantLock BankAccount
mvn exec:java -Dexec.mainClass="synchronised.locking.Main"
```
