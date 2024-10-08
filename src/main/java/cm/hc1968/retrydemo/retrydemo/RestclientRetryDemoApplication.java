package cm.hc1968.retrydemo.retrydemo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.retry.annotation.EnableRetry;

@SpringBootApplication
@EnableRetry
public class RestclientRetryDemoApplication {

	private static final Logger log = LoggerFactory.getLogger(RestclientRetryDemoApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(RestclientRetryDemoApplication.class, args);
	}

//	@Bean
//	ApplicationRunner applicationRunner(SpireClient spireClient) {
//		return args -> {
//			String hello = spireClient.getFromSpire();
//			log.info("Result: %s".formatted(hello));
//		};
//	}


}
