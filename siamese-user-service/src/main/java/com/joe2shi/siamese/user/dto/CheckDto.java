package com.joe2shi.siamese.user.dto;

import lombok.Data;

@Data
public class CheckDto {
    private String data;
    private Integer type; // 1:username  2:phone number
}
