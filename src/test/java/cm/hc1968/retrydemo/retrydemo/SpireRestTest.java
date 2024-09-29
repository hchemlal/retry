package cm.hc1968.retrydemo.retrydemo;


import cm.hc1968.retrydemo.retrydemo.service.SpireMessage;
import cm.hc1968.retrydemo.retrydemo.service.SpireResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.client.MockRestServiceServer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;


//@EnableRetry
//@ExtendWith(SpringExtension.class)
@RestClientTest(SpireClient.class)
//@SpringBootTest(classes = SpireClient.class)
class SpireRestTest {

    @Autowired
    MockRestServiceServer server;

    @Autowired
    SpireClient spireClient;

    //PostClient postClient;
//    @Autowired
//    SpireMessage spireMessage;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    public void shouldReturnAllPosts() throws JsonProcessingException {
        // given
//        List<SpireMessage> data = List.of(
//                new SpireMessage("AuctionResult1", 5.67),
//                new SpireMessage("AuctionResult2", 5.67)
//        );

        SpireMessage spireMessage = new SpireMessage("AuctionResult", 5.67);
        SpireResponse expextedSpireResponse = new SpireResponse(true, "Message received");


        // when
        this.server
                //.expect(requestTo("https://jsonplaceholder.typicode.com/posts"))
                .expect(requestTo("http://localhost:8080/postToSpire"))
                .andRespond(withSuccess(objectMapper.writeValueAsString(expextedSpireResponse), MediaType.APPLICATION_JSON));

        // then
        //List<SpireMessage> posts = postClient.postToSpire(spireMessage);
        ResponseEntity<SpireResponse> spireResponseResponseEntity = spireClient.postToSpire(spireMessage);

        assertThat(spireResponseResponseEntity.getStatusCode().value()).isEqualTo(HttpStatus.OK.value());
        assertTrue(Objects.requireNonNull(spireResponseResponseEntity.getBody()).okStatus());
    }

}
