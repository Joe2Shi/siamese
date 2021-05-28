package com.joe2shi.siamese.item.service;

import com.joe2shi.siamese.common.vo.SiameseResult;
import com.joe2shi.siamese.item.dto.InsertArticleDto;
import com.joe2shi.siamese.item.dto.UpdateArticleDto;

import java.util.List;

@SuppressWarnings("rawtypes")
public interface ArticleService {
    /**
     * New article
     *
     * @param insertArticle New article information
     * @param userId User ID
     * @return
     */
    SiameseResult insertArticle(InsertArticleDto insertArticle, String userId);

    /**
     * Update article
     *
     * @param updateArticle Update article information
     * @return
     */
    SiameseResult updateArticle(UpdateArticleDto updateArticle);

    /**
     * Paging filter query articles
     *
     * @param key    Filter content
     * @param page   Current page
     * @param rows   Page size
     * @param sortBy Sort field
     * @param desc   true: descending
     * @param userId User ID
     *               false: ascending
     * @return
     */
    SiameseResult selectArticleByPage(String key, Integer page, Integer rows, String sortBy, Boolean desc, String userId);

    /**
     * Delete articles in batches according to ids
     *
     * @param ids Articles ids
     * @return
     */
    SiameseResult deleteArticleByIds(List<String> ids);
}
