package com.joe2shi.siamese.item.service;

import com.joe2shi.siamese.common.vo.SiameseResult;

@SuppressWarnings("rawtypes")
public interface ArticleService {
    /**
     * Paging filter query articles
     * @param key   Filter content
     * @param page  Current page
     * @param rows  Page size
     * @param sortBy    Sort field
     * @param desc  true: descending
     *              false: ascending
     * @return
     */
    SiameseResult selectBrandByPage(String key, Integer page, Integer rows, String sortBy, Boolean desc);
}
