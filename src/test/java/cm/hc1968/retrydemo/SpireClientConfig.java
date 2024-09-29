package cm.hc1968.retrydemo;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

//@SpringBootConfiguration
public class SpireClientConfig {
    //@Bean
    public RestClient restClient(RestClient.Builder builder) {
        return builder.build();
    }
}
