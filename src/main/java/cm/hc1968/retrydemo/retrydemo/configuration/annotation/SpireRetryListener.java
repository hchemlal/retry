package cm.hc1968.retrydemo.retrydemo.configuration.annotation;

import org.springframework.retry.RetryCallback;
import org.springframework.retry.RetryContext;
import org.springframework.retry.RetryListener;
import org.springframework.stereotype.Component;

@Component
public class SpireRetryListener implements RetryListener { //
   // private RetryCondition retryCondition;
    private Throwable lastException;

   // public CustomRetryListener(RetryCondition retryCondition) {
   public SpireRetryListener() {
        //this.retryCondition = retryCondition;
    }

    @Override
    public <T, E extends Throwable> boolean open(RetryContext context, RetryCallback<T, E> callback) {
        // This method is called before the retry operation starts.
        // You can perform any setup or logging here.
        System.out.println(">>>>>OPEN::Starting retry. Retry Count: " + context);
        return true;
    }

    @Override
    public <T, E extends Throwable> void close(RetryContext context, RetryCallback<T, E> callback, Throwable throwable) {
        // This method is called after a retry operation is completed, regardless of success or failure.
        // You can perform any cleanup or logging here.
        System.out.println(">>>>>CLOSE::Retry complete. Retry Count: " + context.getRetryCount());
        System.out.println(">>>>CLOSE::context.isExhaustedOnly()= " + context.isExhaustedOnly());
    }

    @Override
    public <T, E extends Throwable> void onError(RetryContext context, RetryCallback<T, E> callback, Throwable throwable) {
        // This method is called when an error occurs during a retry attempt.
        // You can perform any error handling or logging here.
        System.out.println(">>>>ON-ERROR::Retry failed. Retry Count: " + context.getRetryCount());

        lastException = throwable;
    }

    public Throwable getLastException() {
        return lastException;
    }
}
