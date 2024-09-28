package cm.hc1968.retrydemo.service;


import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.PostExchange;

public interface SpireClient {


    @GetExchange("/fromSpire")
    String getFromSpire();

    @PostExchange("/postToSpire")
    String postToSpire(@RequestBody SpireMessage spireMessage);

}
