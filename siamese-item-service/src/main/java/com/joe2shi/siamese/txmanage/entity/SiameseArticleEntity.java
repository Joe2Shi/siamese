package com.joe2shi.siamese.txmanage.entity;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Table(name = "siamese_article")
public class SiameseArticleEntity {
    @Id
    private String id;
    private String title;
    private String subtitle;
    private String url;
    private Long createTime;
    private Long updateTime;
}
