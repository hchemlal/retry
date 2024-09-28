package cm.hc1968.retrydemo.configuration.annotation;

import org.springframework.core.annotation.AliasFor;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;
import cm.hc1968.retrydemo.exception.RequestTimeoutHttpClientErrorException;
import cm.hc1968.retrydemo.exception.TooEarlyHttpClientErrorException;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ ElementType.METHOD, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)

/**
 * Spire retriable using exponential backoff strategy
 */
@Retryable(
        backoff=@Backoff(delay=1000, multiplier=3),
        retryFor = {
                RequestTimeoutHttpClientErrorException.class,
                TooEarlyHttpClientErrorException.class,
                HttpServerErrorException.BadGateway.class,
                HttpServerErrorException.GatewayTimeout.class,
                HttpServerErrorException.ServiceUnavailable.class,
                HttpClientErrorException.TooManyRequests.class,
                ResourceAccessException.class
        }
)
public @interface SpireExponentialBackoffRetry {
        //@Value("${spire.max.attempts}")
        int spireMaxAttempts=3;

        //@Value("${spire.delay:1000}")
        long spireDelay = 1000L;

        //@Value("${spire.multiplier:2}")
        double spireMultiplier = 2.0;

        // String recover() default "";
        @AliasFor(annotation = Retryable.class, attribute = "recover")
        String recover() default "";

        //int maxAttempts() default 3;
        @AliasFor(annotation = Retryable.class, attribute = "maxAttempts")
        int maxAttempts() default spireMaxAttempts;

//        @AliasFor(annotation = Retryable.class, attribute = "listeners")
//        String[] spireListeners() default {};

//        @AliasFor(annotation = Retryable.class, attribute = "backoff")
//        Backoff backoff() default @Backoff(delay=spireDelay, multiplier=spireMultiplier);

//        @AliasFor(annotation = Backoff.class, attribute = "delay")
//        long delay() default spireDelay;
//
//        @AliasFor(annotation = Backoff.class, attribute = "multiplier")
//        double multiplier() default spireMultiplier;


//        @AliasFor(annotation = Retryable.class, attribute = "listeners")
//        String[] listeners() default {"arcadiaListener"};

        //@AliasFor(annotation = Retryable.class, attribute = "backoff")
//        @AliasFor("spireStrategy")
//        SpireBackoffStrategy spireStrategy() default @SpireBackoffStrategy;



}
