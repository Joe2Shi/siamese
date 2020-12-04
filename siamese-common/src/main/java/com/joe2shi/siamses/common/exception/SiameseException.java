package com.joe2shi.siamses.common.exception;

import com.joe2shi.siamses.common.enums.ExceptionEnum;
import lombok.Getter;

@Getter
public class SiameseException extends RuntimeException {

    private Integer code;
    private String message;

    public SiameseException(ExceptionEnum exceptionEnum) {
        this.code = exceptionEnum.getCode();
        this.message = exceptionEnum.getMessage();
    }

}
