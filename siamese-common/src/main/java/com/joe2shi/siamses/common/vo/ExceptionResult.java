package com.joe2shi.siamses.common.vo;

import com.joe2shi.siamses.common.exception.SiameseException;
import lombok.Getter;

@Getter
public class ExceptionResult {
    private int code;
    private String message;
    private Long timestamp;

    public ExceptionResult(SiameseException siameseException) {
        this.code = siameseException.getCode();
        this.message = siameseException.getMessage();
        this.timestamp = System.currentTimeMillis();
    }
}