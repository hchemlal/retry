package cm.hc1968.retrydemo.retrydemo.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.client.HttpClientErrorException;

import java.nio.charset.Charset;

public final class RequestTimeoutHttpClientErrorException extends HttpClientErrorException {

    public RequestTimeoutHttpClientErrorException() {
        super(HttpStatus.REQUEST_TIMEOUT);
    }

    public RequestTimeoutHttpClientErrorException(HttpStatusCode statusCode, HttpHeaders headers) {
        super(statusCode, "", headers, null, Charset.defaultCharset());
    }
}
