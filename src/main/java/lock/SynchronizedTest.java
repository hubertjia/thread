package lock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SynchronizedTest {

    public static void main(String[] args) {

//        Bank bank = new Bank(10);
//        Bank bank1 = new Bank(10);


//        MonitorTest<Integer> monitorTest = new MonitorTest<>();
        ReenTrantLockTest synchronizedTest = new ReenTrantLockTest();
        Perso perso = new Perso(synchronizedTest);
        Person2 person2 = new Person2(synchronizedTest);
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.submit(perso);
        executorService.submit(person2);
        executorService.shutdown();
    }
}
