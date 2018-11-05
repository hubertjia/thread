package threadPoolRelation;


import java.util.concurrent.RecursiveTask;

public class CountTask extends RecursiveTask {

    private static final int THRESHOLD = 2;
    private int start;
    private int end;

    public CountTask(int start, int end){
        this.start = start;
        this.end = end;
    }


    @Override
    protected Integer compute() {
        int sum = 0;
        int temp = end - start;
        if (temp <= THRESHOLD) {
            for (int i = start ; i <= end ;i ++) {
                sum += i;
            }
        } else {
            int middle = (start + end) / 2;
            CountTask count1 = new CountTask(start,middle);
            CountTask count2 = new CountTask(middle +1,end);
            count1.fork();
            count2.fork();
            Integer result1 = (Integer) count1.join();
            Integer result2 = (Integer) count2.join();
            sum = result1 + result2;
        }
        return sum;
    }

}
