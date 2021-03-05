package com.joe2shi.siamese.user.bo;

import lombok.Data;

@Data
public class CheckBo {
    private String data;
    private Integer type; // 1:username  2:phone number
}
