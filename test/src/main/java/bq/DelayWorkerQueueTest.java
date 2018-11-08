package bq;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.atomic.AtomicInteger;

public class DelayWorkerQueueTest {
    public static void main(String[] args) {

        ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(5);

        AtomicInteger count = new AtomicInteger();
        count.getAndDecrement();
    }
}
