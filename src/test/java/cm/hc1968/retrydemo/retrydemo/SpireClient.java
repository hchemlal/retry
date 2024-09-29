package cm.hc1968.retrydemo.retrydemo;

import cm.hc1968.retrydemo.retrydemo.exception.RequestTimeoutHttpClientErrorException;
import cm.hc1968.retrydemo.retrydemo.service.SpireMessage;
import cm.hc1968.retrydemo.retrydemo.service.SpireResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON;

@Service
public class SpireClient {

    private static final Logger log = LoggerFactory.getLogger(SpireClient.class);
    private final RestClient restClient;

    /*
     * The RestClient and RestClientCustomizer are created in Application.java
     * This is a workaround for the RestClientTest issue and more about that can be
     * found here https://github.com/spring-projects/spring-boot/issues/38832
     */
    public SpireClient(RestClient.Builder restClientBuilder) {
        this.restClient = restClientBuilder.baseUrl("http://localhost:8080").build();
    }

    public List<SpireMessage> findAll() {
        return restClient.get()
                .uri("/postToSpire")
                .retrieve()
                .body(new ParameterizedTypeReference<>() {});
    }

    public ResponseEntity<SpireResponse> postToSpire(SpireMessage spireMessage) {


        return restClient.post()
                .uri( "/postToSpire")
                .contentType(APPLICATION_JSON)
                .body(spireMessage)
                .retrieve()
                /* By default, RestClient throws a subclass of RestClientException when retrieving a response with a 4xx or 5xx status code.
                This behavior can be overridden using onStatus to throw a custom exception.
                 */
//                .onStatus(HttpStatusCode::is4xxClientError, (request, response) -> {
//                    throw new MyCustomRuntimeException(response.getStatusCode(), response.getHeaders());
//                })

                .toEntity(SpireResponse.class);

    }

    public ResponseEntity<SpireResponse> postToSpireUsingExchange(SpireMessage spireMessage) {


//        return restClient.post()
//                .uri( "/postToSpire")
//                .accept(APPLICATION_JSON)
//                .exchange((request, response) -> {
//                    if (response.getStatusCode().is4xxClientError()) {
//                        throw new RequestTimeoutHttpClientErrorException(response.getStatusCode(), response.getHeaders());
//
//                    }
//                    else {
//                        //SpireResponse spireResponse = convertResponse(response);
//                       // return spireResponse;
//                        return response;
//                    }
//                });
        return null;

    }
}