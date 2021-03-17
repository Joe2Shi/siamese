package com.joe2shi.siamese.common.vo;

import lombok.Data;

import java.util.List;

@Data
public class SiamesePageResult<T> {
    private Long total;
    private Long totalPage;
    private List<T> items;

    public SiamesePageResult(Long total, List<T> items) {
        this.total = total;
        this.items = items;
    }
}
