package lock;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ReenTrantLockTest<T> {

    private ReentrantLock lock = new ReentrantLock();

    private Condition isNotEmpty = lock.newCondition();

    private Condition isFull = lock.newCondition();

    private Queue<T> queue = new LinkedList<>();

    public T getValue () {

        lock.lock();

        while (queue.size() == 0) {
            try {
                isNotEmpty.await();
            } catch (InterruptedException e) {

            }
        }
        T result = queue.poll();
        isFull.signal();
        lock.unlock();

        return result;
    }

    public void set(T value) {
        lock.lock();
        while (queue.size() > 5) {
            try {
                isFull.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        queue.add(value);
        isNotEmpty.signal();
        lock.unlock();
    }

}
