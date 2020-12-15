package com.joe2shi.siamese.file.service.impl;

import com.github.tobato.fastdfs.domain.fdfs.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import com.joe2shi.siamese.common.constant.FileGroupConstant;
import com.joe2shi.siamese.common.constant.LoggerConstant;
import com.joe2shi.siamese.common.enums.ResponseEnum;
import com.joe2shi.siamese.common.exception.SiameseException;
import com.joe2shi.siamese.file.config.FileProperties;
import com.joe2shi.siamese.file.entity.SiameseFileEntity;
import com.joe2shi.siamese.file.mapper.FileMapper;
import com.joe2shi.siamese.file.service.MarkdownService;
import com.joe2shi.siamese.common.vo.BaseResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Service
@Slf4j
public class MarkdownServiceImpl implements MarkdownService {

    @Autowired
    private FileProperties fileProperties;
    @Autowired
    private FastFileStorageClient fastFileStorageClient;
    @Autowired
    private FileMapper fileMapper;

    @Override
    public BaseResult uploadMarkdown(MultipartFile file) {
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
            return new BaseResult(ResponseEnum.UPLOAD_MARKDOWN_SUCCESS);
        } catch (IOException e) {
            log.error(LoggerConstant.UPLOAD_MARKDOWN_FAILED + e);
            throw new SiameseException(ResponseEnum.UPLOAD_MARKDOWN_FAILED);
        }
    }

}
