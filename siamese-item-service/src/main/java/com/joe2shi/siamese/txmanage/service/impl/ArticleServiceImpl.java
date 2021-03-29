package com.joe2shi.siamese.txmanage.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.page.PageMethod;
import com.joe2shi.siamese.common.constant.SystemConstant;
import com.joe2shi.siamese.common.enums.ResponseEnum;
import com.joe2shi.siamese.common.exception.SiameseException;
import com.joe2shi.siamese.common.utils.IdUtils;
import com.joe2shi.siamese.common.vo.SiamesePageResult;
import com.joe2shi.siamese.common.vo.SiameseResult;
import com.joe2shi.siamese.txmanage.bo.InsertArticleBo;
import com.joe2shi.siamese.txmanage.entity.SiameseArticleEntity;
import com.joe2shi.siamese.txmanage.mapper.ArticleMapper;
import com.joe2shi.siamese.txmanage.service.ArticleService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

@Service
@Slf4j
@SuppressWarnings("rawtypes")
public class ArticleServiceImpl implements ArticleService {
    @Resource
    private ArticleMapper articleMapper;

    @Override
    public SiameseResult insertArticle(InsertArticleBo insertArticle) {
        String title = insertArticle.getTitle();
        String subtitle = insertArticle.getSubtitle();
        String url = insertArticle.getUrl();
        if (StringUtils.isBlank(title)) {
            throw new SiameseException(ResponseEnum.TITLE_IS_REQUIRED);
        }
        if (StringUtils.isBlank(subtitle)) {
            throw new SiameseException(ResponseEnum.SUBTITLE_IS_REQUIRED);
        }
        if (StringUtils.isBlank(url)) {
            throw new SiameseException(ResponseEnum.URL_IS_REQUIRED);
        }
        SiameseArticleEntity siameseArticleEntity = new SiameseArticleEntity();
        siameseArticleEntity.setId(IdUtils.generateId());
        siameseArticleEntity.setTitle(title);
        siameseArticleEntity.setSubtitle(subtitle);
        siameseArticleEntity.setUrl(url);
        siameseArticleEntity.setCreateTime(System.currentTimeMillis());
        siameseArticleEntity.setUpdateTime(siameseArticleEntity.getCreateTime());
        int result = articleMapper.insert(siameseArticleEntity);
        if (result < SystemConstant.NUMBER_ONE) {
            throw new SiameseException(ResponseEnum.ADD_ARTICLE_FAILED);
        }
        return new SiameseResult(ResponseEnum.OPERATING_SUCCESS);
    }

    @Override
    public SiameseResult selectArticleByPage(String key, Integer page, Integer rows, String sortBy, Boolean desc) {
        PageMethod.startPage(page, rows);
        Example example = new Example(SiameseArticleEntity.class);
        if (!StringUtils.isBlank(key)) {
            example.createCriteria()
//                .orLike(SystemConstant.STRING_TAG, SystemConstant.CHARACTER_PERCENT_SIGN + key + SystemConstant.CHARACTER_PERCENT_SIGN)
                .orLike(SystemConstant.STRING_TITLE, SystemConstant.CHARACTER_PERCENT_SIGN + key + SystemConstant.CHARACTER_PERCENT_SIGN);
        }
        if (!StringUtils.isBlank(sortBy)) {
            String sort = sortBy + (Boolean.TRUE.equals(desc) ? SystemConstant.CHARACTER_SPACE + SystemConstant.STRING_ASC : SystemConstant.CHARACTER_SPACE + SystemConstant.STRING_DESC);
            example.setOrderByClause(sort);
        }
        Page<SiameseArticleEntity> pageInfo = (Page<SiameseArticleEntity>) articleMapper.selectByExample(example);
        return new SiameseResult<>(ResponseEnum.REQUEST_ACCEPTED, new SiamesePageResult<>(pageInfo.getTotal(), pageInfo));
    }

    @Override
    public SiameseResult deleteArticleByIds(List<String> ids) {
        if (CollectionUtils.isEmpty(ids)) {
            throw new SiameseException(ResponseEnum.IDS_IS_REQUIRED);
        }
        int result = articleMapper.deleteByIdList(ids);
        if (result < SystemConstant.NUMBER_ONE) {
            throw new SiameseException(ResponseEnum.RECORD_NOT_FOUND);
        }
        return new SiameseResult(ResponseEnum.OPERATING_SUCCESS);
    }
}
