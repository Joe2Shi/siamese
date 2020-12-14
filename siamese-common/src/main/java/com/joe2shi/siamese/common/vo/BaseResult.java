package com.joe2shi.siamese.common.vo;

import com.joe2shi.siamese.common.enums.ResponseEnum;
import lombok.Data;

@Data
public class BaseResult {

    private Integer code;
    private String message;
    private Long timestamp;

    public BaseResult(ResponseEnum responseEnum) {
        this.code = responseEnum.getCode();
        this.message = responseEnum.getMessage();
        this.timestamp = System.currentTimeMillis();
    }

}