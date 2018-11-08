package lock;

import java.util.Random;

public class Perso implements Runnable {

    //private Bank bank;

    private ReenTrantLockTest synchronizedTest;
    private MonitorTest<Integer> monitorTest;
    public Perso() {
    }

    public Perso(ReenTrantLockTest synchronizedTest) {
        this.synchronizedTest = synchronizedTest;
    }
//    public Perso(MonitorTest monitorTest) {
//        this.monitorTest = monitorTest;
//    }

    //    public Perso(Bank bank) {
//        this.bank = bank;
//    }

    @Override
    public void run() {
        int cout =10;
        for (int i = 0; i < 10 ;i ++){
            System.out.println("增加的值是：" + i);
            synchronizedTest.set(i);
            cout--;
        }
    }
}
