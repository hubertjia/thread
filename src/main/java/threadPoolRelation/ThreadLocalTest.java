package threadPoolRelation;

public class ThreadLocalTest {

    public static void main(String[] args) {

        ThreadLocal threadLocal = new ThreadLocal();
        threadLocal.set(1);
    }
}
