package com.joe2shi.siamses.common.vo;

import com.joe2shi.siamses.common.enums.ResponseEnum;
import lombok.Data;

@Data
public class DataResult<T> {

    private int code;
    private String message;
    private Long timestamp;
    private T data;

    public DataResult(ResponseEnum responseEnum, T data) {
        this.code = responseEnum.getCode();
        this.message = responseEnum.getMessage();
        this.data = data;
        this.timestamp = System.currentTimeMillis();
    }

}
