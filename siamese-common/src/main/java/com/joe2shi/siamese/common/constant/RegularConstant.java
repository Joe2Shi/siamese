package com.joe2shi.siamese.common.constant;

/**
 * Regular Expression Constant
 */
public class RegularConstant {
    private RegularConstant() {
    }

    public static final String CHECK_USERNAME = "^[a-zA-Z0-9_]{3,15}$";
    public static final String CHECK_SECRET_CODE = "^(?=.*[0-9])(?=.*[a-zA-Z])(.{6,20})$";
    public static final String CHECK_PHONE_NUMBER = "^((13[0-9])|(14[0,1,4-9])|(15[0-3,5-9])|(16[2,5,6,7])|(17[0-8])|(18[0-9])|(19[0-3,5-9]))\\d{8}$";
}
