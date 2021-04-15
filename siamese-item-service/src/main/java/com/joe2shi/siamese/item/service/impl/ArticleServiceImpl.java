package com.joe2shi.siamese.item.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.page.PageMethod;
import com.joe2shi.siamese.common.constant.SystemConstant;
import com.joe2shi.siamese.common.enums.ResponseEnum;
import com.joe2shi.siamese.common.exception.SiameseException;
import com.joe2shi.siamese.common.utils.IdUtils;
import com.joe2shi.siamese.common.vo.SiamesePageResult;
import com.joe2shi.siamese.common.vo.SiameseResult;
import com.joe2shi.siamese.item.dto.DeleteFilesDto;
import com.joe2shi.siamese.item.dto.InsertArticleDto;
import com.joe2shi.siamese.item.dto.UpdateArticleDto;
import com.joe2shi.siamese.item.entity.SiameseArticleEntity;
import com.joe2shi.siamese.item.mapper.ArticleMapper;
import com.joe2shi.siamese.item.proxy.FileServiceProxy;
import com.joe2shi.siamese.item.service.ArticleService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@SuppressWarnings("rawtypes")
public class ArticleServiceImpl implements ArticleService {
    @Resource
    private ArticleMapper articleMapper;

    @Resource
    private FileServiceProxy fileServiceProxy;

    @Override
    public SiameseResult insertArticle(InsertArticleDto insertArticle) {
        SiameseResult result;
        String title = insertArticle.getTitle();
        String subtitle = insertArticle.getSubtitle();
        MultipartFile file = insertArticle.getFile();
        if (StringUtils.isBlank(title)) {
            throw new SiameseException(ResponseEnum.TITLE_IS_REQUIRED);
        }
        if (StringUtils.isBlank(subtitle)) {
            throw new SiameseException(ResponseEnum.SUBTITLE_IS_REQUIRED);
        }
        if (ObjectUtils.isEmpty(file)) {
            throw new SiameseException(ResponseEnum.FILE_IS_REQUIRED);
        }
        // Upload markdown file
        result = fileServiceProxy.uploadFile(file, SystemConstant.STRING_MARKDOWN);
        if (!ObjectUtils.isEmpty(result) && SystemConstant.SUCCESS_CODE == result.getCode()) {
            // Save article information to database
            SiameseArticleEntity siameseArticleEntity = new SiameseArticleEntity();
            siameseArticleEntity.setId(IdUtils.generateId());
            siameseArticleEntity.setTitle(title);
            siameseArticleEntity.setSubtitle(subtitle);
            siameseArticleEntity.setAddress(String.valueOf(result.getData()));
            siameseArticleEntity.setCreateTime(System.currentTimeMillis());
            siameseArticleEntity.setUpdateTime(siameseArticleEntity.getCreateTime());
            int insert = articleMapper.insert(siameseArticleEntity);
            if (insert < SystemConstant.NUMBER_ONE) {
                throw new SiameseException(ResponseEnum.ADD_ARTICLE_FAILED);
            }
            result = new SiameseResult(ResponseEnum.OPERATING_SUCCESS);
        }
        return result;
    }

    @Override
    public SiameseResult updateArticle(UpdateArticleDto updateArticle) {
        SiameseArticleEntity siameseArticleEntity = new SiameseArticleEntity();
        SiameseResult result;
        String id = updateArticle.getId();
        String title = updateArticle.getTitle();
        String subtitle = updateArticle.getSubtitle();
        MultipartFile file = updateArticle.getFile();
        String oldAddress = updateArticle.getOldAddress();
        if (StringUtils.isBlank(id)) {
            throw new SiameseException(ResponseEnum.ID_IS_REQUIRED);
        }
        if (!ObjectUtils.isEmpty(file) && file.getSize() > 0) {
            if (StringUtils.isBlank(oldAddress)) {
                throw new SiameseException(ResponseEnum.MARKDOWN_OLD_ADDRESSES_IS_REQUIRED);
            }
            // Delete original file
            ArrayList<String> addresses = new ArrayList<>();
            addresses.add(oldAddress);
            deleteFile(addresses);
            // Upload markdown file
            result = fileServiceProxy.uploadFile(file, SystemConstant.STRING_MARKDOWN);
            if (!ObjectUtils.isEmpty(result) && SystemConstant.SUCCESS_CODE == result.getCode()) {
                // Save article information to database
                siameseArticleEntity.setId(id);
                siameseArticleEntity.setTitle(title);
                siameseArticleEntity.setSubtitle(subtitle);
                siameseArticleEntity.setAddress(String.valueOf(result.getData()));
                siameseArticleEntity.setUpdateTime(System.currentTimeMillis());
                result = updateArticle(siameseArticleEntity);
            }
        } else {
            // Save article information to database
            siameseArticleEntity.setId(id);
            siameseArticleEntity.setTitle(title);
            siameseArticleEntity.setSubtitle(subtitle);
            siameseArticleEntity.setAddress(null);
            siameseArticleEntity.setUpdateTime(System.currentTimeMillis());
            result = updateArticle(siameseArticleEntity);
        }
        return result;
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
        Example example = new Example(SiameseArticleEntity.class);
        example.createCriteria().andIn(SystemConstant.STRING_ID, ids);
        List<SiameseArticleEntity> items = articleMapper.selectByExample(example);
        List<String> addresses = items.stream().map(SiameseArticleEntity::getAddress).collect(Collectors.toList());
        deleteFile(addresses);
        int delete = articleMapper.deleteByIdList(ids);
        if (delete < SystemConstant.NUMBER_ONE) {
            throw new SiameseException(ResponseEnum.DELETE_FAILED);
        }
        return new SiameseResult(ResponseEnum.OPERATING_SUCCESS);
    }


    private SiameseResult updateArticle(SiameseArticleEntity siameseArticleEntity) {
        SiameseResult result;
        int insert = articleMapper.updateByPrimaryKeySelective(siameseArticleEntity);
        if (insert < SystemConstant.NUMBER_ONE) {
            throw new SiameseException(ResponseEnum.ADD_ARTICLE_FAILED);
        }
        result = new SiameseResult(ResponseEnum.OPERATING_SUCCESS);
        return result;
    }

    private void deleteFile(List<String> addresses) {
        SiameseResult result;
        DeleteFilesDto deleteFiles = new DeleteFilesDto();
        deleteFiles.setAddresses(addresses);
        result = fileServiceProxy.deleteFiles(deleteFiles);
        if (ObjectUtils.isEmpty(result) || SystemConstant.SUCCESS_CODE != result.getCode()) {
            throw new SiameseException(ResponseEnum.DELETE_FAILED);
        }
    }
}
