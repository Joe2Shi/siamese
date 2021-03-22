package com.joe2shi.siamese.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public enum HttpHeaderEnum {
    /**
     * Json type
     */
    CONTENT_TYPE_JSON("Content-Type", "application/json; charset=UTF-8")
    ;
    private String name;
    private String value;
}
