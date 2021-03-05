package com.joe2shi.siamese.gateway.utils;

import lombok.Data;

@Data
public class UserInfo {
    private String id;

    public UserInfo(String id) {
        this.id = id;
    }
}
