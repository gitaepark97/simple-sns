package hugo.simplesns.web.support.response;

import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@ControllerAdvice
class ApiResponseHandler implements ResponseBodyAdvice<Object> {

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(
        Object body,
        MethodParameter returnType,
        MediaType selectedContentType,
        Class<? extends HttpMessageConverter<?>> selectedConverterType,
        ServerHttpRequest request,
        ServerHttpResponse response
    ) {
        if (body instanceof ApiResponse<?> apiResponse) {
            response.setStatusCode(apiResponse.status());
            return apiResponse;
        }

        if (body instanceof ResponseEntity<?> entity) {
            HttpStatus status = (HttpStatus) entity.getStatusCode();
            response.setStatusCode(status);
            return ApiResponse.of(status, entity.getBody());
        }

        HttpStatus status = obtainsResponseStatus(returnType);
        response.setStatusCode(status);
        return ApiResponse.of(status, body);
    }

    private HttpStatus obtainsResponseStatus(MethodParameter returnType) {
        ResponseStatus responseStatus = returnType.getMethodAnnotation(ResponseStatus.class);
        return responseStatus == null ? HttpStatus.OK : responseStatus.value();
    }

}
