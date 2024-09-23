package cm.hc1968.retrydemo.hello;

import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClientBuilder;
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager;
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManagerBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;
import cm.hc1968.retrydemo.exception.RequestTimeoutHttpClientErrorException;
import cm.hc1968.retrydemo.exception.TooEarlyHttpClientErrorException;
import cm.hc1968.retrydemo.service.SpireClient;

@Configuration
public class HelloServiceClientConfig {

//    private final RestClient restClient;
//    private final  SpireService spireService;

    @Bean
    public SpireClient spireClientConfig(RestClient.Builder builder) {
//        ClientHttpRequestFactorySettings requestFactorySettings = ClientHttpRequestFactorySettings.DEFAULTS
//                .withConnectTimeout(Duration.ofSeconds(1L))
//                .withReadTimeout(Duration.ofSeconds(5L));
//
//        HttpComponentsClientHttpRequestFactory requestFactory = ClientHttpRequestFactories.get(HttpComponentsClientHttpRequestFactory.class, requestFactorySettings);

        PoolingHttpClientConnectionManager connectionManager =
                PoolingHttpClientConnectionManagerBuilder.create().useSystemProperties().build();
        CloseableHttpClient httpClient =
                HttpClientBuilder.create()
                        .useSystemProperties()
                        .setConnectionManager(connectionManager)
                        .disableAutomaticRetries()  // <-- disable internal retry in Apache HttpClient
                        .build();
        HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory(httpClient);


        RestClient restClient = builder
                .baseUrl("http://localhost:8080")
                .requestFactory(requestFactory)
                .requestInterceptor(
                        (request, body, execution) -> {
                            ClientHttpResponse response = execution.execute(request, body);
                            if (response.getStatusCode() == HttpStatus.REQUEST_TIMEOUT) throw new RequestTimeoutHttpClientErrorException();
                            if (response.getStatusCode() == HttpStatus.TOO_EARLY) throw new TooEarlyHttpClientErrorException();
                            return response;
                        })
                .build();

        /* The following to take advantage of "HTTP service as a Java interface with @HttpExchange methods." */
        RestClientAdapter adapter = RestClientAdapter.create(restClient);
        HttpServiceProxyFactory factory = HttpServiceProxyFactory.builderFor(adapter).build();

       return factory.createClient(SpireClient.class);
    }

//    public SpireService getSpireService(){
//        return this.spireService;
//    }

//    @RestClientRetryable(recover = "recoverMe")
//    public String getHello() {
//        return this.restClient.get()
//                .uri("/hello")
//                .retrieve()
//                .body(String.class);
//    }
//
//    @RestClientRetryable(recover = "recoverMe")
//    public String nonRetriableException() {
//        return this.restClient.get()
//                .uri("/nonRetriableException")
//                .retrieve()
//                .body(String.class);
//    }
//
//    @RestClientRetryable(recover = "recoverMe")
//    public String retriableException() {
//        return this.restClient.get()
//                .uri("/retriableException")
//                .retrieve()
//                .body(String.class);
//    }

//    @Recover
//    public String recoverMe() {
//        return ">>>>>>>>>>>I have recovered!!";
//    }



}
