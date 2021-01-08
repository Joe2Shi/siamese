package com.joe2shi.siamese.file.service.impl;

import com.github.tobato.fastdfs.domain.fdfs.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import com.joe2shi.siamese.common.constant.LoggerConstant;
import com.joe2shi.siamese.common.constant.SystemConstant;
import com.joe2shi.siamese.file.config.FileProperties;
import com.joe2shi.siamese.file.entity.SiameseFileEntity;
import com.joe2shi.siamese.file.mapper.FileMapper;
import com.joe2shi.siamese.file.service.ImageService;
import com.joe2shi.siamese.common.constant.FileGroupConstant;
import com.joe2shi.siamese.common.enums.ResponseEnum;
import com.joe2shi.siamese.common.exception.SiameseException;
import com.joe2shi.siamese.common.vo.SiameseResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
@EnableConfigurationProperties(FileProperties.class)
@SuppressWarnings("rawtypes")
public class ImageServiceImpl implements ImageService {
    @Resource
    private FileProperties fileProperties;
    @Resource
    private FastFileStorageClient fastFileStorageClient;
    @Resource
    private FileMapper fileMapper;

    @Override
    public SiameseResult<String> uploadImage(MultipartFile file) {
        try {
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
            // upload file service
            String extension = StringUtils.substringAfterLast(file.getOriginalFilename(), SystemConstant.STRING_DECIMAL_POINT);
            StorePath storePath = fastFileStorageClient.uploadFile(FileGroupConstant.IMAGE_GROUP, file.getInputStream(), file.getSize(), extension);
            String address = fileProperties.getBaseAddress() + storePath.getFullPath();
            // insert database
            String id = UUID.randomUUID().toString().replaceAll(SystemConstant.STRING_HYPHEN, SystemConstant.STRING_NULL);
            SiameseFileEntity siameseFileEntity = new SiameseFileEntity();
            siameseFileEntity.setId(id);
            siameseFileEntity.setAddress(address);
            siameseFileEntity.setType(FileGroupConstant.IMAGE_GROUP);
            siameseFileEntity.setCreateTime(System.currentTimeMillis());
            int result = fileMapper.insert(siameseFileEntity);
            if (result != 1) {
                // remove from file server
                fastFileStorageClient.deleteFile(storePath.getFullPath());
                throw new SiameseException(ResponseEnum.UPLOAD_FAILED);
            }
            // return result
            return new SiameseResult<>(ResponseEnum.UPLOAD_SUCCESS, address);
        } catch (IOException e) {
            log.error(LoggerConstant.UPLOAD_IMAGE_FAILED + e.getMessage());
            throw new SiameseException(ResponseEnum.UPLOAD_FAILED);
        }
    }

    @Override
    @Transactional
    public SiameseResult deleteImage(String id) {
        // query whether the record exists
        SiameseFileEntity siameseFileEntity = fileMapper.selectByPrimaryKey(id);
        if (ObjectUtils.isEmpty(siameseFileEntity)) {
            throw new SiameseException(ResponseEnum.IMAGE_NOT_FOUND);
        }
        if (!FileGroupConstant.IMAGE_GROUP.equals(siameseFileEntity.getType())) {
            throw new SiameseException(ResponseEnum.IMAGE_NOT_FOUND);
        }
        // delete records in the database
        int result = fileMapper.deleteByPrimaryKey(id);
        if (result != SystemConstant.NUMBER_ONE) {
            throw new SiameseException(ResponseEnum.DELETE_FAILED);
        }
        // get group and path
        String groupAndPath = siameseFileEntity.getAddress().substring(fileProperties.getBaseAddress().length());
        String group = groupAndPath.substring(SystemConstant.NUMBER_ZERO, groupAndPath.indexOf(SystemConstant.STRING_SLASH));
        String path = groupAndPath.substring(groupAndPath.indexOf(SystemConstant.STRING_SLASH) + SystemConstant.NUMBER_ONE);
        // delete real file
        fastFileStorageClient.deleteFile(group, path);
        return new SiameseResult(ResponseEnum.DELETE_SUCCESS);
    }

    @Override
    public SiameseResult<List<SiameseFileEntity>> queryImages() {
        SiameseFileEntity siameseFileEntity = new SiameseFileEntity();
        siameseFileEntity.setType(FileGroupConstant.IMAGE_GROUP);
        List<SiameseFileEntity> items = fileMapper.select(siameseFileEntity);
        if (!CollectionUtils.isEmpty(items))
            return new SiameseResult<>(ResponseEnum.QUERY_SUCCESS, items);
        else
            throw new SiameseException(ResponseEnum.IMAGE_NOT_FOUND);
    }
}
