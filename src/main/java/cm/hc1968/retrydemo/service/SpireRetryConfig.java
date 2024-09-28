package cm.hc1968.retrydemo.service;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpireRetryConfig {

    @Bean
    public SpireMessage createMessage()
    {
        return new SpireMessage("AuctionResult", 5.67);
    }

}
