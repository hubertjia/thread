package lock;

public class Person2 implements Runnable {

      private ReenTrantLockTest reenTrantLockTest;

    public Person2(ReenTrantLockTest reenTrantLockTest) {
        this.reenTrantLockTest = reenTrantLockTest;
    }
    //    private Bank bank;
//    private MonitorTest<Integer> monitorTest;
//    public Person2(Bank bank) {
//        this.bank = bank;
//    }
//
//    public Person2(MonitorTest<Integer> monitorTest) {
//        this.monitorTest = monitorTest;
//    }

    @Override
    public void run() {
//        bank.take2();
        int cout =10;
        for (int i = 0 ;i < 10 ; i++) {
            try {
                System.out.println("获取到的值是： " + reenTrantLockTest.getValue());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
