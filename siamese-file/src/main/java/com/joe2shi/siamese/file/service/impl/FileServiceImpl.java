package com.joe2shi.siamese.file.service.impl;

import com.github.tobato.fastdfs.domain.fdfs.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import com.joe2shi.siamese.common.constant.SystemConstant;
import com.joe2shi.siamese.file.config.FileProperties;
import com.joe2shi.siamese.file.dto.UploadFileDto;
import com.joe2shi.siamese.file.service.FileService;
import com.joe2shi.siamese.common.enums.ResponseEnum;
import com.joe2shi.siamese.common.exception.SiameseException;
import com.joe2shi.siamese.common.vo.SiameseResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;

@Service
@Slf4j
@EnableConfigurationProperties(FileProperties.class)
@SuppressWarnings("rawtypes")
public class FileServiceImpl implements FileService {
    @Resource
    private FileProperties fileProperties;
    @Resource
    private FastFileStorageClient fastFileStorageClient;

    @Override
    public SiameseResult<String> uploadFile(UploadFileDto uploadFile) {
        MultipartFile file = uploadFile.getFile();
        String type = uploadFile.getType();
        if (ObjectUtils.isEmpty(file)) {
            throw new SiameseException(ResponseEnum.FILE_IS_REQUIRED);
        }
        if (StringUtils.isEmpty(type)) {
            throw new SiameseException(ResponseEnum.FILE_TYPE_IS_REQUIRED);
        }
        try {
            if (SystemConstant.STRING_IMAGE.equalsIgnoreCase(type)) {
                // check file type
                String contentType = file.getContentType();
                if (!fileProperties.getAllowImageTypes().contains(contentType)) {
                    throw new SiameseException(ResponseEnum.INVALID_FILE_TYPE);
                }
                // check file content
                BufferedImage image = ImageIO.read(file.getInputStream());
                if (ObjectUtils.isEmpty(image)) {
                    throw new SiameseException(ResponseEnum.INVALID_FILE_TYPE);
                }
            } else if (SystemConstant.STRING_MARKDOWN.equalsIgnoreCase(type)) {
                // check file type
                String contentType = file.getContentType();
                if (!fileProperties.getAllowTextTypes().contains(contentType)) {
                    throw new SiameseException(ResponseEnum.INVALID_FILE_TYPE);
                }
            } else {
                throw new SiameseException(ResponseEnum.INVALID_FILE_TYPE);
            }
            // upload file service
            String extension = StringUtils.substringAfterLast(file.getOriginalFilename(), SystemConstant.CHARACTER_DECIMAL_POINT);
            StorePath storePath = fastFileStorageClient.uploadFile(type.toLowerCase(), file.getInputStream(), file.getSize(), extension);
            String address = fileProperties.getBaseAddress() + storePath.getFullPath();
            // return result
            return new SiameseResult<>(ResponseEnum.OPERATING_SUCCESS, address);
        } catch (IOException e) {
            log.error(ResponseEnum.UPLOAD_FAILED.getMessage() + SystemConstant.CHARACTER_COLON + SystemConstant.CHARACTER_SPACE + e.getMessage());
            throw new SiameseException(ResponseEnum.UPLOAD_FAILED);
        }
    }

    @Override
    public SiameseResult deleteFiles(List<String> addresses) {
        if (CollectionUtils.isEmpty(addresses)) {
            throw new SiameseException(ResponseEnum.ADDRESSES_IS_REQUIRED);
        }
        // delete file
        for (String address : addresses) {
            String groupAndPath = address.substring(fileProperties.getBaseAddress().length());
            String group = groupAndPath.substring(SystemConstant.NUMBER_ZERO, groupAndPath.indexOf(SystemConstant.CHARACTER_SLASH));
            String path = groupAndPath.substring(groupAndPath.indexOf(SystemConstant.CHARACTER_SLASH) + SystemConstant.NUMBER_ONE);
            try {
                fastFileStorageClient.deleteFile(group, path);
            } catch (Exception e) {
                log.warn(e.getMessage() + SystemConstant.CHARACTER_COLON + SystemConstant.CHARACTER_SPACE + address);
            }
        }
        return new SiameseResult(ResponseEnum.OPERATING_SUCCESS);
    }
}
