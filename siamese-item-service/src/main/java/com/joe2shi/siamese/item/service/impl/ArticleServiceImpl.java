package com.joe2shi.siamese.item.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.joe2shi.siamese.common.constant.SystemConstant;
import com.joe2shi.siamese.common.enums.ResponseEnum;
import com.joe2shi.siamese.common.vo.SiamesePageResult;
import com.joe2shi.siamese.common.vo.SiameseResult;
import com.joe2shi.siamese.item.entity.SiameseArticleEntity;
import com.joe2shi.siamese.item.mapper.ArticleMapper;
import com.joe2shi.siamese.item.service.ArticleService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;

@Service
@Slf4j
@SuppressWarnings("rawtypes")
public class ArticleServiceImpl implements ArticleService {
    @Resource
    private ArticleMapper articleMapper;

    @Override
    public SiameseResult selectBrandByPage(String key, Integer page, Integer rows, String sortBy, Boolean desc) {
        PageHelper.startPage(page, rows);
        Example example = new Example(SiameseArticleEntity.class);
        if (!StringUtils.isBlank(key)) {
            example.createCriteria()
//                .orLike(SystemConstant.STRING_TAG, SystemConstant.CHARACTER_PERCENT_SIGN + key + SystemConstant.CHARACTER_PERCENT_SIGN)
                .orLike(SystemConstant.STRING_TITLE, SystemConstant.CHARACTER_PERCENT_SIGN + key + SystemConstant.CHARACTER_PERCENT_SIGN);
        }
        if (!StringUtils.isBlank(sortBy)) {
            String sort = sortBy + (desc ? SystemConstant.CHARACTER_SPACE + SystemConstant.STRING_ASC : SystemConstant.CHARACTER_SPACE + SystemConstant.STRING_DESC);
            example.setOrderByClause(sort);
        }
        Page<SiameseArticleEntity> pageInfo = (Page<SiameseArticleEntity>) articleMapper.selectByExample(example);
        return new SiameseResult<>(ResponseEnum.QUERY_SUCCESS, new SiamesePageResult<>(pageInfo.getTotal(), pageInfo));
    }
}
