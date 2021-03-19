package com.joe2shi.siamese.file.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Table(name = "siamese_file")
public class SiameseFileEntity {
    @Id
    private String id;
    @JsonIgnore
    @Column(name = "`group`")
    private String group;
    private String address;
    private Long createTime;
}
