package com.joe2shi.siamese.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public enum ResponseEnum {
    QUERY_SUCCESS(20000, "query success"),
    UPLOAD_SUCCESS(20000, "upload success"),
    DELETE_SUCCESS(20000, "delete success"),

    INVALID_FILE_TYPE(40000, "invalid file type"),

    IMAGE_NOT_FOUND(40004, "image not found"),
    MARKDOWN_NOT_FOUND(40004, "markdown not found"),

    UPLOAD_FAILED(50000, "upload fail"),
    DELETE_FAILED(50000, "delete fail"),
    PLEASE_TRY_AGAIN_LATER(50000, "please try again later"),
    ;
    private Integer code;
    private String message;
}
