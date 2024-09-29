package cm.hc1968.retrydemo.retrydemo.service;


import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.PostExchange;

public interface SpireClient {

//consider using  public ResponseEntity<SpireResponse> postToSpire(SpireMessage spireMessage)
    @GetExchange("/fromSpire")
    String getFromSpire();

    @PostExchange("/postToSpire")
    String postToSpire(@RequestBody SpireMessage spireMessage);



}
