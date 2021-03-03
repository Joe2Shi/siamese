package com.joe2shi.siamese.auth.entity;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Table(name = "siamese_user")
public class SiameseUserEntity {
    @Id
    private String id;
    private String username;
    private String password;
    private String phoneNumber;
    private String avatar;
    private String motto;
    private String github;
    private Long createTime;
}
