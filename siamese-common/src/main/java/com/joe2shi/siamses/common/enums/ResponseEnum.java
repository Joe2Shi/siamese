package com.joe2shi.siamses.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum ResponseEnum {

    UPLOAD_FILE_SUCCESS(20000, "upload file success"),
    INVALID_FILE_TYPE(40000, "invalid file type"),
    UPLOAD_FILE_FAILED(50000, "upload file fail");
    private Integer code;
    private String message;

}
