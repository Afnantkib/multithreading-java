package synchronised;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.Test;

public class CounterTest {

  @Test
  public void testConcurrentIncrementAndDecrement() throws InterruptedException {
    Counter counter = new Counter();
    int numberOfThreads = 10;
    int operationsPerThread = 1000;
    ExecutorService executor = Executors.newFixedThreadPool(numberOfThreads);

    // Run 5 threads to increment and 5 threads to decrement
    for (int i = 0; i < numberOfThreads; i++) {
      final int threadIndex = i;
      executor.submit(
          () -> {
            for (int j = 0; j < operationsPerThread; j++) {
              if (threadIndex % 2 == 0) {
                counter.increment();
              } else {
                counter.decrement();
              }
            }
          });
    }

    executor.shutdown();
    executor.awaitTermination(5, TimeUnit.SECONDS);

    // Since we have equal number of increment and decrement threads doing same number of ops,
    // final value should be 0 if synchronization is correct.
    assertEquals(
        0, counter.getValue(), "Counter value should be 0 after balanced concurrent operations");
  }
}
