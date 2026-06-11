# ☕ Java Multithreading Playground

A curated reference repository showcasing Java multithreading fundamentals, concurrent design patterns, synchronization techniques, and advanced explicit locking mechanisms.

---

## 📂 Project Structure

The codebase is organized into modular packages, each focusing on a specific dimension of Java concurrency:

*   📂 **[`com.ironbutterfly`](./multithreading/src/main/java/com/ironbutterfly)** — Fundamentals of Thread creation, the Thread Lifecycle, and monitoring Thread States.
*   📂 **[`threadvsrunnable`](./multithreading/src/main/java/threadvsrunnable)** — Design patterns comparing class inheritance (`extends Thread`) vs interface implementation (`implements Runnable`).
*   📂 **[`threadmethods`](./multithreading/src/main/java/threadmethods)** — Control flow and lifecycle management (`yield()`, `sleep()`, `join()`, `interrupt()`), Thread priorities, and Daemon vs. User threads.
*   📂 **[`synchronised`](./multithreading/src/main/java/synchronised)** — Critical section protection using intrinsic locking (`synchronized` methods and blocks) to eliminate race conditions.
*   📂 **[`synchronised.locking`](./multithreading/src/main/java/synchronised/locking)** — Advanced synchronization using extrinsic locking (`ReentrantLock`), fair/unfair lock allocation, timed lock acquisition (`tryLock`), and reentrancy examples.

---

## 💡 Key Concurrency Concepts Illustrated

### 1. Thread Creation & Lifecycle
Java threads transition through distinct lifecycle states. This is illustrated in:
*   [`Main.java`](./multithreading/src/main/java/com/ironbutterfly/Main.java): Creates, starts, and monitors threads.
*   [`MyThread.java`](./multithreading/src/main/java/com/ironbutterfly/MyThread.java): Logs state transitions (`NEW` ➔ `RUNNABLE` ➔ `TIMED_WAITING` ➔ `TERMINATED`) by leveraging `Thread.sleep()` and `Thread.join()`.

#### Thread States Covered:
*   `NEW`: Thread instance created but `start()` not yet invoked.
*   `RUNNABLE`: Running or ready to run, waiting for CPU scheduling.
*   `TIMED_WAITING`: Waiting for another thread to perform an action for up to a specified waiting time (e.g., during `Thread.sleep(ms)`).
*   `TERMINATED`: The thread has completed execution.

---

### 2. Thread vs Runnable Design Choice
Standard guidelines for when to subclass `Thread` versus when to implement `Runnable`:
*   [`A.java`](./multithreading/src/main/java/threadvsrunnable/A.java): Shows implementing `Runnable` to enable multithreading while preserving the ability to extend a parent class (overcoming Java's single inheritance limitation).
*   [`B.java`](./multithreading/src/main/java/threadvsrunnable/B.java): Shows direct subclassing of `Thread` for cases where no other class extension is required.

---

### 3. Core Thread Control Methods & Daemon Threads
Deep-dive into the behavior of key scheduler methods and thread classifications in:
*   [`MyThread.java`](./multithreading/src/main/java/threadmethods/MyThread.java):
    *   `yield()`: Voluntarily pauses current execution to allow scheduler to prioritize other runnable threads.
    *   `setPriority(int)`: Configures Thread Priorities (`MIN_PRIORITY`, `NORM_PRIORITY`, `MAX_PRIORITY`).
    *   `interrupt()`: Requests a thread to halt, resetting or triggering interruption flags.
*   [`DeamonThread.java`](./multithreading/src/main/java/threadmethods/DeamonThread.java):
    *   **User Threads**: Prevent JVM from terminating until all User Threads finish executing.
    *   **Daemon Threads**: Run in the background (e.g., GC) and do not prevent the JVM from shutting down when all User Threads have completed.

---

### 4. Thread Synchronization & Critical Sections
When multiple threads access shared mutable state, race conditions can occur.
*   [`Counter.java`](./multithreading/src/main/java/synchronised/Counter.java): Demonstrates how to make class operations thread-safe.
    *   **Synchronized Method**: `public synchronized void increment()` locks the instance.
    *   **Synchronized Block**: `synchronized(this) { value--; }` restricts scope to block statements, leaving outer lines unsynchronized.
*   [`MyThread.java`](./multithreading/src/main/java/synchronised/MyThread.java): Demonstrates thread safety by launching concurrent threads that continuously mutate a shared `Counter`.

---

### 5. Extrinsic Locking (ReentrantLock)
For complex locking operations, `java.util.concurrent.locks.ReentrantLock` offers richer capabilities than `synchronized` blocks.
*   [`BankAccount.java`](./multithreading/src/main/java/synchronised/locking/BankAccount.java):
    *   Compares intrinsic methods with timed acquisition lock checks:
        ```java
        if (lock.tryLock(1000, TimeUnit.MILLISECONDS)) { ... }
        ```
        This prevents threads from blocking indefinitely, facilitating elegant deadlock avoidance.
*   [`FairUnfairExmaple.java`](./multithreading/src/main/java/synchronised/locking/FairUnfairExmaple.java):
    *   **Fair Locks** (`new ReentrantLock(true)`): Grasp locks in order of request arrival, reducing thread starvation.
    *   **Unfair Locks** (default): Optimize throughput by allowing barging threads to grab available locks.
*   [`ReentrantExample.java`](./multithreading/src/main/java/synchronised/locking/ReentrantExample.java):
    *   Illustrates **Lock Reentrancy** where the same thread can acquire the same lock recursively (e.g., `outerMethod()` calling `innerMethod()`) without triggering a deadlock.

---

## 🛠️ Build & Run Instructions

This project is built using **Maven** and targets **Java 24**.

### Prerequisites
*   Java Development Kit (JDK) 24 or higher
*   Apache Maven

### Build the Project
Compile and build the project artifacts using the Maven Wrapper or local Maven installation:
```bash
mvn clean package
```

### Running Specific Examples
To run any of the demonstration main classes, execute the following from the root directory or inside your preferred IDE:

*   **Thread Lifecycle State Demo**:
    ```bash
    mvn exec:java -Dexec.mainClass="com.ironbutterfly.MyThread"
    ```
*   **Synchronized Counter Demo**:
    ```bash
    mvn exec:java -Dexec.mainClass="synchronised.MyThread"
    ```
*   **BankAccount Locking Demo**:
    ```bash
    mvn exec:java -Dexec.mainClass="synchronised.locking.Main"
    ```
*   **Fair/Unfair Locking Demo**:
    ```bash
    mvn exec:java -Dexec.mainClass="synchronised.locking.FairUnfairExmaple"
    ```
*   **Daemon Threads Demo**:
    ```bash
    mvn exec:java -Dexec.mainClass="threadmethods.DeamonThread"
    ```
