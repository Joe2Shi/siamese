package com.joe2shi.siamese.file.entity;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Table(name = "siamese_file")
public class SiameseFileEntity {
    @Id
    private String id;
    private String address;
    private String type;
}
