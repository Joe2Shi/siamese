package com.joe2shi.siamses.common.exception;

import com.joe2shi.siamses.common.enums.ResponseEnum;
import lombok.Getter;

@Getter
public class SiameseException extends RuntimeException {

    private ResponseEnum responseEnum;

    public SiameseException(ResponseEnum responseEnum) {
        this.responseEnum = responseEnum;
    }

}
