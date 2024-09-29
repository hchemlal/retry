package cm.hc1968.retrydemo.retrydemo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;

public final class TooEarlyHttpClientErrorException extends HttpClientErrorException {

    public TooEarlyHttpClientErrorException() {
        super(HttpStatus.TOO_EARLY);
    }
}
