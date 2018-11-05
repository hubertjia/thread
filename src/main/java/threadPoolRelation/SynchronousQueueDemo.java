package threadPoolRelation;

import java.util.concurrent.SynchronousQueue;

public class SynchronousQueueDemo {

    public static void main(String[] args) {

        SynchronousQueue<Integer> queue = new SynchronousQueue<Integer>(true);
        Integer i = new Integer(1);
        Integer j = 2;
        try {
            queue.put(i);
            queue.put(j);
            queue.take();
            queue.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        Thread putThread = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                for (int i = 0; i < 5 ; i ++) {
//                    try {
//                        System.out.println("put 正在运行------");
//                        queue.put(i);
//                        System.out.println("putThread " + Thread.currentThread().getName()  + " ,put 值为：" + i);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        });
//
//        Thread takeThread = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    for (int i = 0; i < 5; i++) {
//                        System.out.println("take 正在运行----");
//                        System.out.println("takeThread " + Thread.currentThread().getName() + " take 值为： " +  queue.take());
//                        Thread.sleep(200);
//                    }
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//
//        putThread.start();
//
//        takeThread.start();


    }
}
