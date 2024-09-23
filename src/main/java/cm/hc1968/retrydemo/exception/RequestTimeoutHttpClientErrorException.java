package cm.hc1968.retrydemo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;

public final class RequestTimeoutHttpClientErrorException extends HttpClientErrorException {

    public RequestTimeoutHttpClientErrorException() {
        super(HttpStatus.REQUEST_TIMEOUT);
    }
}
