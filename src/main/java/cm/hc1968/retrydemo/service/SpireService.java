package cm.hc1968.retrydemo.service;

import cm.hc1968.retrydemo.configuration.annotation.SpireExponentialBackoffRetry;
import cm.hc1968.retrydemo.exception.RequestTimeoutHttpClientErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.retry.annotation.Recover;
import org.springframework.stereotype.Service;

import static cm.hc1968.retrydemo.service.SpireTestConstants.RECOVERED_FROM_NON_RETRIABLE_EXCEPTIONS;
import static cm.hc1968.retrydemo.service.SpireTestConstants.RECOVERED_FROM_RETRIABLE_EXCEPTIONS;

@Service
public class SpireService {

    @Autowired
    private SpireClient spireClient;

    @SpireExponentialBackoffRetry(recover = "recoverGetFromSpire", maxAttempts = 3)
    public String getFromSpire() throws Exception {
        return spireClient.getFromSpire();
    }

    @SpireExponentialBackoffRetry(recover = "recoverPostToSpire", maxAttempts = 3)
    public String postToSpire(SpireMessage spireMessage) throws Exception {
        return spireClient.postToSpire(spireMessage);
    }

    @Recover
    public String recoverGetFromSpire(Exception exception) {
        if(exception != null) {
            return "I have recovered from getFromSpire after retriable exceptions";
        }
        else
        {
            return "I have recovered from getFromSpire after non retriable exception";
        }

    }




    @Recover
    public String recoverPostToSpire(Exception exception) {
        if(exception instanceof RequestTimeoutHttpClientErrorException)
        {
            return RECOVERED_FROM_RETRIABLE_EXCEPTIONS;
        }
        else
        {

            return RECOVERED_FROM_NON_RETRIABLE_EXCEPTIONS;
        }

    }
}
