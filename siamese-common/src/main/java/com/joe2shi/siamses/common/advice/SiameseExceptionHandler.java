package com.joe2shi.siamses.common.advice;

import com.joe2shi.siamses.common.exception.SiameseException;
import com.joe2shi.siamses.common.vo.BaseResult;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class SiameseExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<BaseResult> handlerException(SiameseException siameseException) {
        return ResponseEntity.ok(new BaseResult(siameseException.getResponseEnum()));
    }

}