package lock;

import com.google.common.util.concurrent.Monitor;

import java.util.LinkedList;
import java.util.Queue;

public class MonitorTest<T> {

    private Queue<T> queue = new LinkedList<>();
    private  Monitor monitor = new Monitor();

    private Monitor.Guard put = new Monitor.Guard(monitor) {
        @Override
        public boolean isSatisfied() {
            return queue.size() < 5;
        }
    };

    private Monitor.Guard get = new Monitor.Guard(monitor) {
        @Override
        public boolean isSatisfied() {
            return queue.size() > 0;
        }
    };


    public void set(T value){

        try {
            monitor.enterWhen(put);

            queue.add(value);
            System.out.println("add 的值是：" + value + "，queue大小是：" +  queue.size());
        } catch (InterruptedException e) {

        }finally {
            monitor.leave();
        }
    }

    public T getValue() throws InterruptedException {

        monitor.enterWhen(get);
        try {
            return queue.poll();
        } catch (Exception e) {

        }finally {
            monitor.leave();
        }
        return null;
    }

}
