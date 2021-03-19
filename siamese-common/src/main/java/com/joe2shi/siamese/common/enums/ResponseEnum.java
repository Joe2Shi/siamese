package com.joe2shi.siamese.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public enum ResponseEnum {
    /**
     * Don't prompt
     */
    REQUEST_ACCEPTED(10000, "Request accepted"),
    WRONG_PASSWORD(10001, "Wrong password"),
    /**
     * 200
     */
    OPERATING_SUCCESS(20000, "Operating success"),
    REGISTER_SUCCESS(20000, "Register success"),
    SIGN_IN_SUCCESS(20000, "Sign in success"),
    ACCREDIT_SUCCESS(20000, "Accredit success"),
    /**
     * 400
     */
    INVALID_FILE_TYPE(40000, "Invalid file type"),
    INVALID_USERNAME(40001, "Invalid username"),
    INVALID_PASSWORD(40002, "Invalid password"),
    INVALID_PHONE_NUMBER(40003, "Invalid phone number"),
    INVALID_USER_DATA_TYPE(40005, "Invalid user data type"),
    USERNAME_ALREADY_USE(40006, "Username already use"),
    PHONE_NUMBER_HAS_BEEN_BOUND(40007, "Phone number has been bound"),
    USERNAME_IS_REQUIRED(40008, "Username is required"),
    PASSWORD_IS_REQUIRED(40009, "Password is required"),
    IDS_IS_REQUIRED(40010, "Ids is required"),
    /**
     * 404
     */
    RECORD_NOT_FOUND(40400, "Record not found"),
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
