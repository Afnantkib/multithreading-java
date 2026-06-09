package synchronised;

public class Counter {

    int value = 0;
    // synchronized can be used before method
    public synchronized void increment() {
        value++;
    }

    public void decrement() {
        // synchronized can be inside as a block as well
        synchronized (this){
            value--;
        }

        // anything outside the block is not synchronized
    }

    public int getValue() {
        return value;
    }
}
