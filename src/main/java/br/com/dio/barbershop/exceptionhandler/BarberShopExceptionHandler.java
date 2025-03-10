package br.com.dio.barbershop.exceptionhandler;

import br.com.dio.barbershop.controller.response.ProblemResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.OffsetDateTime;

@Log4j2
@ControllerAdvice
public class BarberShopExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleAllExceptions(final Exception exception, final WebRequest request) {
        log.error("handleAllExceptions", exception);
        var status = HttpStatus.INTERNAL_SERVER_ERROR;
        var response = ProblemResponse.builder()
                .status(status.value())
                .timestamp(OffsetDateTime.now())
                .message(exception.getMessage())
                .build();

        return handleExceptionInternal(exception, response, new HttpHeaders(), status,  request);
    }
}
