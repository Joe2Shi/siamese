package com.joe2shi.siamese.upload.service.impl;

import com.github.tobato.fastdfs.domain.fdfs.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import com.joe2shi.siamese.upload.config.UploadProperties;
import com.joe2shi.siamese.upload.service.UploadService;
import com.joe2shi.siamses.common.enums.ResponseEnum;
import com.joe2shi.siamses.common.exception.SiameseException;
import com.joe2shi.siamses.common.vo.DataResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@Slf4j
@EnableConfigurationProperties(UploadProperties.class)
public class UploadServiceImpl implements UploadService {

    private static final Logger logger = LoggerFactory.getLogger(UploadServiceImpl.class);

    @Autowired
    private FastFileStorageClient fastFileStorageClient;

    @Autowired
    private UploadProperties uploadProperties;

    @Override
    public DataResult<String> uploadImage(MultipartFile file) {
        try {
            String extension = StringUtils.substringAfterLast(file.getOriginalFilename(), ".");
            StorePath storePath = fastFileStorageClient.uploadFile("markdown", file.getInputStream(), file.getSize(), extension);
            return new DataResult<>(ResponseEnum.REQUEST_SUCCESS, uploadProperties.getBaseAddress() + storePath.getFullPath());
        } catch (IOException e) {
            throw new SiameseException(ResponseEnum.UPLOAD_FILE_FAILED);
        }
    }
}
