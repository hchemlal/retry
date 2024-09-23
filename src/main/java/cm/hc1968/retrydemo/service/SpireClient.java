package cm.hc1968.retrydemo.service;

import org.springframework.retry.annotation.Recover;
import org.springframework.web.service.annotation.GetExchange;
import cm.hc1968.retrydemo.configuration.annotation.SpireExponentialBackoffRetry;

public interface SpireClient {

    @SpireExponentialBackoffRetry(recover = "recoverHello", maxAttempts = 3)
    @GetExchange("/hello")
    String getHello();

    @SpireExponentialBackoffRetry(recover = "recoverMe")
    @GetExchange("/nonRetriableException")
    String nonRetriableException();

    @SpireExponentialBackoffRetry(recover = "recoverRetriableException")
    @GetExchange("/retriableException")
    String retriableException();


    @Recover
    default String recoverHello() {
        return ">>>>>>>>>>>I have recovered /hello!!";
    }

    @Recover
    default String recoverRetriableException() {
        return ">>>>>>>>>>>I have recovered /retriableException!!";
    }
}
