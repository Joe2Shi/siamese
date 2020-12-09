package com.joe2shi.siamses.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum ResponseEnum {

    REQUEST_SUCCESS(20000, "Upload File Success"),
    UPLOAD_FILE_FAILED(50000, "Upload File Fail");
    private Integer code;
    private String message;

}
