package com.joe2shi.siamese.common.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SiamesePageResult<T> {
    private Long total;
    private List<T> items;
}
