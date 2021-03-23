package com.joe2shi.siamese.common.utils;

import com.joe2shi.siamese.common.constant.SystemConstant;
import org.apache.commons.lang.StringUtils;

public class ObjectUtils {
    private ObjectUtils() {
    }

    public static String toString(Object obj) {
        if (obj == null) {
            return null;
        }
        return obj.toString();
    }

    public static Long toLong(Object obj) {
        if (obj == null) {
            return 0L;
        }
        if (obj instanceof Double || obj instanceof Float) {
            return Long.valueOf(StringUtils.substringBefore(obj.toString(), SystemConstant.CHARACTER_DECIMAL_POINT));
        }
        if (obj instanceof Number) {
            return Long.valueOf(obj.toString());
        }
        if (obj instanceof String) {
            return Long.valueOf(obj.toString());
        } else {
            return 0L;
        }
    }

    public static Integer toInt(Object obj) {
        return toLong(obj).intValue();
    }
}
