package com.joe2shi.siamese.common.constant;

/**
 * Regular Expression Constant
 */
public class RegularConstant {
    public static final String checkUsername = "^[a-zA-Z0-9_]{3,15}$";
    public static final String checkPassword = "^(?=.*[0-9])(?=.*[a-zA-Z])(.{6,20})$";
}
