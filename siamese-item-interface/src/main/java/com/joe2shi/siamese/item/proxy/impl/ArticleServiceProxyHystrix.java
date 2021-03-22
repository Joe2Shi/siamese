package com.joe2shi.siamese.item.proxy.impl;

import com.joe2shi.siamese.common.enums.ResponseEnum;
import com.joe2shi.siamese.common.vo.SiameseResult;
import com.joe2shi.siamese.item.bo.InsertArticleBo;
import com.joe2shi.siamese.item.proxy.ArticleServiceProxy;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@SuppressWarnings("rawtypes")
public class ArticleServiceProxyHystrix implements ArticleServiceProxy {
    @Override
    public SiameseResult insertArticle(InsertArticleBo insertArticle) {
        return new SiameseResult(ResponseEnum.PLEASE_TRY_AGAIN_LATER);
    }

    @Override
    public SiameseResult selectArticleByPage(String key, Integer page, Integer rows, String sortBy, Boolean desc) {
        return new SiameseResult(ResponseEnum.PLEASE_TRY_AGAIN_LATER);
    }

    @Override
    public SiameseResult deleteArticleByIds(List<String> ids) {
        return new SiameseResult(ResponseEnum.PLEASE_TRY_AGAIN_LATER);
    }
}
