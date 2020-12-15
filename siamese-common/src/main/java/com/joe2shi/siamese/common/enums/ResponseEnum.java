package com.joe2shi.siamese.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public enum ResponseEnum {

    UPLOAD_IMAGE_SUCCESS(20000, "upload image success"),
    DELETE_IMAGE_SUCCESS(20000, "delete image success"),
    UPLOAD_MARKDOWN_SUCCESS(20000, "upload markdown success"),
    INVALID_FILE_TYPE(40000, "invalid file type"),
    IMAGE_NOT_FOUND(40004, "image not found"),
    UPLOAD_IMAGE_FAILED(50000, "upload image fail"),
    DELETE_IMAGE_FAILED(50000, "delete image fail"),
    UPLOAD_MARKDOWN_FAILED(50000, "upload markdown failed"),
    ;
    private Integer code;
    private String message;

}
