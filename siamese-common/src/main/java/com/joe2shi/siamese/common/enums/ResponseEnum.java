package com.joe2shi.siamese.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public enum ResponseEnum {
    /**
     * 200
     */
    REGISTER_SUCCESS(20000, "Register success"),
    SIGN_IN_SUCCESS(20000, "Sign in success"),
    QUERY_SUCCESS(20000, "Query success"),
    UPLOAD_SUCCESS(20000, "Upload success"),
    DELETE_SUCCESS(20000, "Delete success"),
    /**
     * 400
     */
    INVALID_FILE_TYPE(40001, "Invalid file type"),
    INVALID_USERNAME(40002, "Invalid username"),
    INVALID_PASSWORD(40003, "Invalid password"),
    WRONG_USERNAME_OR_PASSWORD(40003, "Invalid password"),
    /**
     * 404
     */
    IMAGE_NOT_FOUND(40401, "Image not found"),
    MARKDOWN_NOT_FOUND(40402, "Markdown not found"),
    /**
     * 500
     */
    REGISTER_FAILED(50001, "Register fail"),
    UPLOAD_FAILED(50001, "Upload fail"),
    DELETE_FAILED(50002, "Delete fail"),
    PLEASE_TRY_AGAIN_LATER(50003, "Please try again later"),
    ;
    private Integer code;
    private String message;
}
