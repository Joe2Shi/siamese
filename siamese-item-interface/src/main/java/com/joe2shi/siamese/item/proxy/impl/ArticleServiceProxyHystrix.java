package com.joe2shi.siamese.item.proxy.impl;

import com.joe2shi.siamese.common.enums.ResponseEnum;
import com.joe2shi.siamese.common.vo.SiameseResult;
import com.joe2shi.siamese.item.proxy.ArticleServiceProxy;
import org.springframework.stereotype.Component;

@Component
@SuppressWarnings("rawtypes")
public class ArticleServiceProxyHystrix implements ArticleServiceProxy {
    @Override
    public SiameseResult selectBrandByPage(String key, Integer page, Integer rows, String sortBy, Boolean desc) {
        return new SiameseResult(ResponseEnum.PLEASE_TRY_AGAIN_LATER);
    }
}
