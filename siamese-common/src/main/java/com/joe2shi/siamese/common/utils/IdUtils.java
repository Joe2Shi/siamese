package com.joe2shi.siamese.common.utils;

import com.joe2shi.siamese.common.constant.SystemConstant;

import java.util.UUID;

public class IdUtils {
    private IdUtils() {
    }

    public static String generateId() {
        return UUID.randomUUID().toString().replaceAll(SystemConstant.CHARACTER_HYPHEN, SystemConstant.CHARACTER_NULL);
    }
}
