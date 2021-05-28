package com.joe2shi.siamese.common.advice;

import com.joe2shi.siamese.common.enums.ResponseEnum;
import com.joe2shi.siamese.common.exception.SiameseException;
import com.joe2shi.siamese.common.vo.SiameseResult;
import com.netflix.zuul.exception.ZuulException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.IOException;

@ControllerAdvice
@Slf4j
@SuppressWarnings("rawtypes")
public class SiameseExceptionHandler {
    @ExceptionHandler(SiameseException.class)
    public ResponseEntity<SiameseResult> handlerException(SiameseException siameseException) {
        return ResponseEntity.ok(new SiameseResult(siameseException.getResponseEnum()));
    }

    @ExceptionHandler(IOException.class)
    public ResponseEntity<SiameseResult> ioException(IOException ioException) {
        log.error(ioException.getMessage());
        return ResponseEntity.ok(new SiameseResult(ResponseEnum.PLEASE_TRY_AGAIN_LATER));
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<SiameseResult> runtimeException(RuntimeException runtimeException) {
        log.error(runtimeException.getMessage());
        return ResponseEntity.ok(new SiameseResult(ResponseEnum.PLEASE_TRY_AGAIN_LATER));
    }

    @ExceptionHandler(ZuulException.class)
    public ResponseEntity<SiameseResult> zuulException(ZuulException zuulException) {
        log.error(zuulException.getMessage());
        return ResponseEntity.ok(new SiameseResult(ResponseEnum.PLEASE_TRY_AGAIN_LATER));
    }
}
