package cm.hc1968.retrydemo.configuration.annotation;

import org.springframework.core.annotation.AliasFor;
import org.springframework.retry.annotation.Backoff;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented

@Backoff(

        delay=1000,
        multiplier=2
)
public @interface SpireBackoffStrategy {
    @AliasFor(annotation = Backoff.class, attribute = "delay")
    int delay() default 1000;

    @AliasFor(annotation = Backoff.class, attribute = "multiplier")
    int multiplier() default 2;
}
