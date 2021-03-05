package com.joe2shi.siamese.user.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Table(name = "siamese_user")
public class SiameseUserEntity {
    @Id
    @JsonIgnore
    private String id;
    private String username;
    @JsonIgnore
    private String password;
    @JsonIgnore
    private String phoneNumber;
    private String avatar;
    private String motto;
    private String github;
    private Long createTime;
}
