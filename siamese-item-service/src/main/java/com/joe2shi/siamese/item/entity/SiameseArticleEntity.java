package com.joe2shi.siamese.item.entity;

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
    private String createTime;
    private Long updateTime;
}
