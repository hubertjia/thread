package threadPoolRelation;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CountDownLactchTesk {


    public static void main(String[] args) {
        int count = 3;
        CountDownLatch countDownLatch = new CountDownLatch(count);

        ExecutorService executorService = Executors.newFixedThreadPool(count);

        KafkaService kafkaService1= new KafkaService(countDownLatch,"service1");
        KafkaService kafkaService2 = new KafkaService(countDownLatch,"service2");
        KafkaService kafkaService3 = new KafkaService(countDownLatch,"service3");

        executorService.submit(kafkaService1);
        executorService.submit(kafkaService2);
        executorService.submit(kafkaService3);
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("所有线程都执行完");

    }
}




class KafkaService implements Runnable {

    private CountDownLatch co;
    private String serviceName;
    public KafkaService(){}
    public KafkaService(CountDownLatch co, String serviceName){
        this.co = co;
        this.serviceName = serviceName;
    }

    @Override
    public void run() {
        System.out.println(serviceName + " is start------------");
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            co.countDown();
        }
        System.out.println(serviceName + " is started-----------");
    }
}

