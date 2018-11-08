package threadPoolRelation;

import com.github.rholder.retry.RetryException;
import com.github.rholder.retry.Retryer;
import com.github.rholder.retry.RetryerBuilder;
import com.github.rholder.retry.StopStrategies;
import com.google.common.base.Predicates;

import java.io.IOException;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;

public class GuavaRetryTest {



    public static void main(String[] args) {
        Retryer<Boolean> retryer = RetryerBuilder.<Boolean>newBuilder()
                .retryIfResult(Predicates.<Boolean>isNull())
                .retryIfResult(Predicates.equalTo(false))
                .retryIfExceptionOfType(IOException.class)
                .retryIfRuntimeException()
                .withStopStrategy(StopStrategies.stopAfterAttempt(5))
                .build();
        try {
            retryer.call(new RetryWorker());
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (RetryException e) {
            e.printStackTrace();
        }
    }
}

class RetryWorker implements Callable<Boolean> {

    @Override
    public Boolean call() throws Exception {

        int i = 2;
        i++;
        if (i == 2){
            System.out.println("数据返回成功！i= " + i);
            return true;
        }else {
            System.out.println("数据返回失败！");
            return false;
        }
    }
}