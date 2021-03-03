package com.joe2shi.siamese.auth.bo;

import lombok.Data;

@Data
public class RegisterBo {
    private String username;
    private String password;
    private String phoneNumber;
    private String avatar;
    private String github;
}
