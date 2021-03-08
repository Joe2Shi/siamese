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
    INVALID_FILE_TYPE(40000, "Invalid file type"),
    INVALID_USERNAME(40001, "Invalid username"),
    INVALID_PASSWORD(40002, "Invalid password"),
    INVALID_PHONE_NUMBER(40003, "Invalid phone number"),
    WRONG_USERNAME_OR_PASSWORD(40004, "Wrong username or password"),
    INVALID_USER_DATA_TYPE(40005, "Invalid user data type"),
    USERNAME_ALREADY_USE(40006, "Username already use"),
    PHONE_NUMBER_HAS_BEEN_BOUND(40007, "Phone number has been bound"),
    /**
     * 404
     */
    USER_INFORMATION_NOT_FOUND(40401, "User information not found"),
    IMAGE_NOT_FOUND(40402, "Image not found"),
    MARKDOWN_NOT_FOUND(40403, "Markdown not found"),
    /**
     * 500
     */
    PLEASE_TRY_AGAIN_LATER(50000, "Please try again later"),
    REGISTER_FAILED(50001, "Register fail"),
    UPLOAD_FAILED(50002, "Upload fail"),
    DELETE_FAILED(50003, "Delete fail"),
    INIT_KEY_FAILED(50004, "Init key failed"),
    GENERATE_TOKEN_FAILED(50005, "Generate token failed"),
    ;
    private Integer code;
    private String message;
}
