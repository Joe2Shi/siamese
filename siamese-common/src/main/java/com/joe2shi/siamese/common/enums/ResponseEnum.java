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
    VALIDATION_SUCCESS(20000, "Validation success"),
    SIGN_IN_SUCCESS(20000, "Sign in success"),
    ACCREDIT_SUCCESS(20000, "Accredit success"),
    QUERY_SUCCESS(20000, "Query success"),
    UPLOAD_SUCCESS(20000, "Upload success"),
    DELETE_SUCCESS(20000, "Delete success"),
    /**
     * 400
     */
    INVALID_FILE_TYPE(40001, "Invalid file type"),
    INVALID_USERNAME(40002, "Invalid username"),
    INVALID_PASSWORD(40003, "Invalid password"),
    INVALID_PHONE_NUMBER(40004, "Invalid phone number"),
    WRONG_USERNAME_OR_PASSWORD(40005, "Wrong username or password"),
    INVALID_USER_DATA_TYPE(40006, "Invalid user data type"),
    USERNAME_ALREADY_EXISTS(40007, "Username already exists"),
    PHONE_NUMBER_HAS_BEEN_BOUND(40008, "Phone number has been bound"),
    /**
     * 403
     */
    REQUEST_FORBIDDEN_ACCESS(40300, "Request forbidden access"),
    /**
     * 404
     */
    USER_INFORMATION_NOT_FOUND(40401, "User information not found"),
    IMAGE_NOT_FOUND(40402, "Image not found"),
    MARKDOWN_NOT_FOUND(40403, "Markdown not found"),
    /**
     * 500
     */
    REGISTER_FAILED(50001, "Register fail"),
    UPLOAD_FAILED(50002, "Upload fail"),
    DELETE_FAILED(50003, "Delete fail"),
    PLEASE_TRY_AGAIN_LATER(50004, "Please try again later"),
    INIT_KEY_FAILED(50005, "Init key failed"),
    GENERATE_TOKEN_FAILED(50006, "Generate token failed"),
    ;
    private Integer code;
    private String message;
}
