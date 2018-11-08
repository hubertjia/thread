package lock;

public class Bank {

    private static int count;
    public Bank() {
    }

    public Bank(int count) {
        this.count = count;
    }

    public static synchronized void take(){
        count--;
        System.out.println(Thread.currentThread().getId()+ " 取出一元，还剩：" + count);
    }

    public synchronized void take2(){
        count = count -2;
        System.out.println(Thread.currentThread().getId()+ "取出两元，还剩" + count);
    }
}
