package threadPoolRelation;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Test {
    public static void main(String[] args) {
//        LinkedBlockingQueue link = new LinkedBlockingQueue();
//        AtomicInteger i = new AtomicInteger();
//        for (int j =0 ; j < 5; j++) {
//
//            System.out.print("初始值：" + i.get() + "增加后：" + i.getAndIncrement());
//        }


        LinkedBlockQueueCustom link = new LinkedBlockQueueCustom();
        ExecutorService service = Executors.newFixedThreadPool(5);
        service.submit(new Runnable() {
            @Override
            public void run() {
                String s = "";
                int i =0;
                for (; ; ) {
                    System.out.println("id:" + Thread.currentThread().getId() + ",i 是：" + i);
                     s = Thread.currentThread().getId() + "" + (i++);
                    try {
//                        link.put(s);
                        Thread.sleep(20000);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });


        ExecutorService service2 = Executors.newFixedThreadPool(5);
        service2.submit(new Runnable() {
            @Override
            public void run() {
                for ( ;; ) {
//                    System.out.println( "取的线程是：" + Thread.currentThread()+ "取的值：" + link.take() + ",容量大小是：" + link.capacity);
                }
            }
        });

    }
}
