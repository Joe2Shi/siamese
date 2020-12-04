package com.joe2shi.siamses.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum ExceptionEnum {

    REQUEST_SUCCESS(20000, "Request Success")
    ;
    private Integer code;
    private String message;

}
