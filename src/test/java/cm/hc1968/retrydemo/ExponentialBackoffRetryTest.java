package cm.hc1968.retrydemo;


import cm.hc1968.retrydemo.exception.RequestTimeoutHttpClientErrorException;
import cm.hc1968.retrydemo.service.SpireClient;
import cm.hc1968.retrydemo.service.SpireMessage;
import cm.hc1968.retrydemo.service.SpireService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.context.annotation.Bean;
import org.springframework.retry.annotation.EnableRetry;

import static cm.hc1968.retrydemo.service.SpireTestConstants.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@SpringBootTest
@EnableRetry
class ExponentialBackoffRetryTest {

    @SpyBean
    private SpireService spireService;

    @MockBean
    private SpireClient spireClient;

    @Autowired
    SpireMessage spireMessage;

    @Test
    void testRetriableAndSucceedOnFirstTry() throws Exception {
        Mockito.when(spireService.postToSpire(spireMessage))
                .thenReturn(PASSED_AT_FIRST_TRY);

        String response = spireService.postToSpire(spireMessage);
        Mockito.verify(spireService, Mockito.times(1)).postToSpire(spireMessage);
        Mockito.verify(spireService, Mockito.times(0))
                .recoverPostToSpire(null);

        assertThat(response, is(PASSED_AT_FIRST_TRY));

    }


    @Test
    void testRetriableExceptionsAndSucceedOnSecondTry() throws Exception {
        Mockito.when(spireService.postToSpire(spireMessage))
                .thenThrow(new RequestTimeoutHttpClientErrorException())
                .thenReturn(PASSED_AT_SECOND_TRY);

        String response = spireService.postToSpire(spireMessage);
        Mockito.verify(spireService, Mockito.times(2)).postToSpire(spireMessage);
        Mockito.verify(spireService, Mockito.times(0))
                .recoverPostToSpire(null);

        assertThat(response, is(PASSED_AT_SECOND_TRY));

    }


    @Test
    void testExhaustRetriableExceptionsThenRecover() throws Exception {
        Mockito.when(spireService.postToSpire(spireMessage))
                .thenThrow(new RequestTimeoutHttpClientErrorException())
                .thenThrow(new RequestTimeoutHttpClientErrorException())
                .thenThrow(new RequestTimeoutHttpClientErrorException());

        String response = spireService.postToSpire(spireMessage);

        Mockito.verify(spireService, Mockito.times(3)).postToSpire(spireMessage);
        Mockito.verify(spireService, Mockito.times(1))
                .recoverPostToSpire(Mockito.any(RequestTimeoutHttpClientErrorException.class));

        assertThat(response, is(RECOVERED_FROM_RETRIABLE_EXCEPTIONS));

    }


    @Test
    void testNonRetriableExceptionThenRecover() throws Exception {
        Mockito.when(spireService.postToSpire(spireMessage))
                .thenThrow(new Exception())

                /* Should not execute the following, should fail at the above */
                .thenThrow(new RequestTimeoutHttpClientErrorException())
                .thenThrow(new RequestTimeoutHttpClientErrorException());

        String response = spireService.postToSpire(spireMessage);

        Mockito.verify(spireService, Mockito.times(1)).postToSpire(spireMessage);
        Mockito.verify(spireService, Mockito.times(1))
                .recoverPostToSpire(Mockito.any(Exception.class));

        assertThat(response, is(RECOVERED_FROM_NON_RETRIABLE_EXCEPTIONS));

    }


}