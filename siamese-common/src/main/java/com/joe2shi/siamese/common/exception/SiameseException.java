package com.joe2shi.siamese.common.exception;

import com.joe2shi.siamese.common.enums.ResponseEnum;
import lombok.Getter;

@Getter
public class SiameseException extends RuntimeException {
    private ResponseEnum responseEnum;

    public SiameseException(ResponseEnum responseEnum) {
        super(responseEnum.getMessage());
        this.responseEnum = responseEnum;
    }
}
