package com.joe2shi.siamese.common.vo;

import com.joe2shi.siamese.common.enums.ResponseEnum;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SiameseResult<T> {
    private int code;
    private String message;
    private Long timestamp;
    private T data;

    public SiameseResult(ResponseEnum responseEnum) {
        this.code = responseEnum.getCode();
        this.message = responseEnum.getMessage();
        this.timestamp = System.currentTimeMillis();
    }

    public SiameseResult(ResponseEnum responseEnum, T data) {
        this.code = responseEnum.getCode();
        this.message = responseEnum.getMessage();
        this.data = data;
        this.timestamp = System.currentTimeMillis();
    }
}
