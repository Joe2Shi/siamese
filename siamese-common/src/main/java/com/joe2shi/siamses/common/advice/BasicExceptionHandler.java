package com.joe2shi.siamses.common.advice;

import com.joe2shi.siamses.common.exception.SiameseException;
import com.joe2shi.siamses.common.vo.ExceptionResult;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class BasicExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ExceptionResult> handlerException(SiameseException siameseException) {
        return ResponseEntity.ok(new ExceptionResult(siameseException));
    }

}