package com.joe2shi.siamese.item.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.joe2shi.siamese.common.constant.SystemConstant;
import lombok.Data;
import org.apache.commons.lang3.time.DateFormatUtils;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Data
@Table(name = "siamese_article")
public class SiameseArticleEntity {
    @Id
    private String id;
    private String title;
    private String subtitle;
    private String address;
    @JsonIgnore
    private Long createTime;
    @JsonIgnore
    private Long updateTime;
    // Show Data
    @Transient
    private String createTimeFormat;
    @Transient
    private String updateTimeFormat;

    public String getCreateTimeFormat() {
        return DateFormatUtils.format(createTime, SystemConstant.PATTERN_DATE);
    }

    public String getUpdateTimeFormat() {
        return DateFormatUtils.format(createTime, SystemConstant.PATTERN_DATE);
    }
}
