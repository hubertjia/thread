package threadPoolRelation;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;

public class ForkJoinTest {
    public static void main(String[] args) {
        ForkJoinPool pool=new ForkJoinPool();
        CountTask countTask = new CountTask(1,100);
        long start=System.currentTimeMillis();
        Future result=pool.submit(countTask);
        try {
            System.out.println("result is:"+result.get());
            long time=System.currentTimeMillis()-start;
            System.out.println("cost time is:"+time);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}



