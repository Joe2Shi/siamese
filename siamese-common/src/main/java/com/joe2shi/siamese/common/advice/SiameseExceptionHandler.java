package com.joe2shi.siamese.common.advice;

import com.joe2shi.siamese.common.exception.SiameseException;
import com.joe2shi.siamese.common.vo.SiameseResult;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@SuppressWarnings("rawtypes")
public class SiameseExceptionHandler {
    @ExceptionHandler(SiameseException.class)
    public ResponseEntity<SiameseResult> handlerException(SiameseException siameseException) {
        return ResponseEntity.ok(new SiameseResult(siameseException.getResponseEnum()));
    }
}
