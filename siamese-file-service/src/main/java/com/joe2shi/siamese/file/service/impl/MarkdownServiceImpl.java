package com.joe2shi.siamese.file.service.impl;

import com.github.tobato.fastdfs.domain.fdfs.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import com.joe2shi.siamese.common.constant.FileGroupConstant;
import com.joe2shi.siamese.common.constant.LoggerConstant;
import com.joe2shi.siamese.common.enums.ResponseEnum;
import com.joe2shi.siamese.common.exception.SiameseException;
import com.joe2shi.siamese.common.vo.SiameseResult;
import com.joe2shi.siamese.file.config.FileProperties;
import com.joe2shi.siamese.file.entity.SiameseFileEntity;
import com.joe2shi.siamese.file.mapper.FileMapper;
import com.joe2shi.siamese.file.service.MarkdownService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.UUID;

@Service
@Slf4j
@SuppressWarnings("rawtypes")
public class MarkdownServiceImpl implements MarkdownService {
    @Resource
    private FileProperties fileProperties;
    @Resource
    private FastFileStorageClient fastFileStorageClient;
    @Resource
    private FileMapper fileMapper;

    @Override
    public SiameseResult uploadMarkdown(MultipartFile file) {
        try {
            // check file type
            String contentType = file.getContentType();
            if (!fileProperties.getAllowTextTypes().contains(contentType)) {
                throw new SiameseException(ResponseEnum.INVALID_FILE_TYPE);
            }
            // upload file service
            String extension = StringUtils.substringAfterLast(file.getOriginalFilename(), ".");
            StorePath storePath = fastFileStorageClient.uploadFile(FileGroupConstant.MARKDOWN_GROUP, file.getInputStream(), file.getSize(), extension);
            String address = fileProperties.getBaseAddress() + storePath.getFullPath();
            // insert database
            String id = UUID.randomUUID().toString().replaceAll("-", "");
            SiameseFileEntity siameseFileEntity = new SiameseFileEntity();
            siameseFileEntity.setId(id);
            siameseFileEntity.setAddress(address);
            siameseFileEntity.setType(FileGroupConstant.MARKDOWN_GROUP);
            int result = fileMapper.insert(siameseFileEntity);
            if (result != 1) {
                // remove from file server
                fastFileStorageClient.deleteFile(storePath.getFullPath());
                throw new SiameseException(ResponseEnum.UPLOAD_MARKDOWN_FAILED);
            }
            // return result
            return new SiameseResult(ResponseEnum.UPLOAD_MARKDOWN_SUCCESS);
        } catch (IOException e) {
            log.error(LoggerConstant.UPLOAD_MARKDOWN_FAILED + e.getMessage());
            throw new SiameseException(ResponseEnum.UPLOAD_MARKDOWN_FAILED);
        }
    }

    @Override
    @Transactional
    public SiameseResult deleteMarkdown(String id) {
        // query whether the record exists
        SiameseFileEntity siameseFileEntity = fileMapper.selectByPrimaryKey(id);
        if (siameseFileEntity == null) {
            throw new SiameseException(ResponseEnum.MARKDOWN_NOT_FOUND);
        }
        // delete records in the database
        int result = fileMapper.deleteByPrimaryKey(id);
        if (result != 1) {
            throw new SiameseException(ResponseEnum.DELETE_MARKDOWN_FAILED);
        }
        // get group and path
        String groupAndPath = siameseFileEntity.getAddress().substring(fileProperties.getBaseAddress().length());
        String group = groupAndPath.substring(0, groupAndPath.indexOf("/"));
        String path = groupAndPath.substring(groupAndPath.indexOf("/") + 1);
        // delete real file
        fastFileStorageClient.deleteFile(group, path);
        return new SiameseResult(ResponseEnum.DELETE_MARKDOWN_SUCCESS);
    }
}
