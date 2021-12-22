package com.example.currencycalc.common;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.nio.file.AccessDeniedException;

@ControllerAdvice
public class ExceptionController {

    // 400
    @ExceptionHandler({RuntimeException.class})
    public ResponseEntity<Object> badRequestException(final RuntimeException ex) {
        return ResponseEntity.ok().body(
                DefaultRes.builder()
                        .data(ex.getMessage())
                        .statusCode(StatusCode.BAD_REQUEST)
                        .responseMessage(ResponseMessage.BAD_REQUEST)
                        .build()
        );
    }

    @ExceptionHandler({ IllegalArgumentException.class })
    public ResponseEntity argumentsException (final AccessDeniedException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("ExceptionHandler 테스트"); //ex.getMessage()
    }

    // 401
    @ExceptionHandler({AccessDeniedException.class})
    public ResponseEntity handleAccessDeniedException(final AccessDeniedException ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ex.getMessage());
    }

    // 400
    @ExceptionHandler({ Exception.class })
    public ResponseEntity<Object> handleAll(final Exception ex) {
        return ResponseEntity.ok().body(
                DefaultRes.builder()
                        .data(ex.getMessage())
                        .statusCode(StatusCode.BAD_REQUEST)
                        .responseMessage(ResponseMessage.BAD_REQUEST)
                        .build()
        );
    }


}
