package cm.hc1968.retrydemo.configuration.annotation;


import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@ControllerAdvice
public class SpireAnnotationInterceptor implements ResponseBodyAdvice {
    @Override
    public boolean supports(MethodParameter methodParameter, Class converterType) {
        return (methodParameter.hasMethodAnnotation(SpireExponentialBackoffRetry.class) );
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter MethodParameter, MediaType selectedContentType, Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        //Your logic
        return null;
    }


}