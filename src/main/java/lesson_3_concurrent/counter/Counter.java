package lesson_3_concurrent.counter;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Counter {

    private int value;
    private final Lock lock;

    public Counter() {
        this.lock = new ReentrantLock();
    }

    public int getValue() {
        return value;
    }

    public void increase() {
        lock.lock();
        value++;
        lock.unlock();
    }

}
