package threadPoolRelation;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CyclicBarrierTest {

    public static  Random random = new Random();


    public static void main(String[] args) {
        int cout = 5;
        CyclicBarrier barrier = new CyclicBarrier(cout, new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getId() + "宣布所有的线程都写入完毕！可以去干其他的活儿了");
            }
        });

        ExecutorService pool = Executors.newFixedThreadPool(cout);
        for (int i = 0; i < cout ;  i++ ) {
            pool.submit(new Worker(barrier));
        }
    }
}



class Worker implements Runnable{

    private CyclicBarrier cyclicBarrier;

    public Worker(){}

    public Worker(CyclicBarrier cyclicBarrier) {
        this.cyclicBarrier = cyclicBarrier;
    }

    @Override
    public void run() {
        int temp = CyclicBarrierTest.random.nextInt(100);
        System.out.println( Thread.currentThread().getId() + ": 我需要写" + temp +"ms 写数据");
        try {
            Thread.sleep(temp);
            System.out.println(Thread.currentThread().getId() + " 数据写入完毕，等待其他小伙伴");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            try {
                cyclicBarrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }

        System.out.println(Thread.currentThread().getId() + "等待完毕！继续干其他活儿");

    }
}
