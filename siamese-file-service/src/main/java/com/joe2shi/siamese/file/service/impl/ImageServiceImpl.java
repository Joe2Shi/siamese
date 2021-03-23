package com.joe2shi.siamese.file.service.impl;

import com.github.tobato.fastdfs.domain.fdfs.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import com.joe2shi.siamese.common.constant.SystemConstant;
import com.joe2shi.siamese.common.utils.IdUtils;
import com.joe2shi.siamese.file.config.FileProperties;
import com.joe2shi.siamese.file.entity.SiameseFileEntity;
import com.joe2shi.siamese.file.mapper.FileMapper;
import com.joe2shi.siamese.file.service.ImageService;
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
            String extension = StringUtils.substringAfterLast(file.getOriginalFilename(), SystemConstant.CHARACTER_DECIMAL_POINT);
            StorePath storePath = fastFileStorageClient.uploadFile(SystemConstant.STRING_IMAGE, file.getInputStream(), file.getSize(), extension);
            String address = fileProperties.getBaseAddress() + storePath.getFullPath();
            // insert database
            SiameseFileEntity siameseFileEntity = new SiameseFileEntity();
            siameseFileEntity.setId(IdUtils.generateId());
            siameseFileEntity.setGroup(SystemConstant.UPPERCASE_LETTER_I);
            siameseFileEntity.setAddress(address);
            siameseFileEntity.setCreateTime(System.currentTimeMillis());
            int result = fileMapper.insert(siameseFileEntity);
            if (result < SystemConstant.NUMBER_ONE) {
                // remove from file server
                fastFileStorageClient.deleteFile(storePath.getFullPath());
                throw new SiameseException(ResponseEnum.UPLOAD_FAILED);
            }
            // return result
            return new SiameseResult<>(ResponseEnum.OPERATING_SUCCESS, address);
        } catch (IOException e) {
            log.error(ResponseEnum.UPLOAD_FAILED.getMessage() + SystemConstant.CHARACTER_COLON + SystemConstant.CHARACTER_SPACE + e.getMessage());
            throw new SiameseException(ResponseEnum.UPLOAD_FAILED);
        }
    }

    @Override
    @Transactional
    public SiameseResult deleteImage(String id) {
        // query whether the record exists
        SiameseFileEntity siameseFileEntity = fileMapper.selectByPrimaryKey(id);
        if (ObjectUtils.isEmpty(siameseFileEntity) || !SystemConstant.UPPERCASE_LETTER_I.equals(siameseFileEntity.getGroup())) {
            throw new SiameseException(ResponseEnum.RECORD_NOT_FOUND);
        }
        // delete records in the database
        int result = fileMapper.deleteByPrimaryKey(id);
        if (result < SystemConstant.NUMBER_ONE) {
            throw new SiameseException(ResponseEnum.DELETE_FAILED);
        }
        // get group and path
        String groupAndPath = siameseFileEntity.getAddress().substring(fileProperties.getBaseAddress().length());
        String group = groupAndPath.substring(SystemConstant.NUMBER_ZERO, groupAndPath.indexOf(SystemConstant.CHARACTER_SLASH));
        String path = groupAndPath.substring(groupAndPath.indexOf(SystemConstant.CHARACTER_SLASH) + SystemConstant.NUMBER_ONE);
        // delete real file
        fastFileStorageClient.deleteFile(group, path);
        return new SiameseResult(ResponseEnum.OPERATING_SUCCESS);
    }

    @Override
    public SiameseResult<List<SiameseFileEntity>> queryImages() {
        SiameseFileEntity siameseFileEntity = new SiameseFileEntity();
        siameseFileEntity.setGroup(SystemConstant.UPPERCASE_LETTER_I);
        List<SiameseFileEntity> items = fileMapper.select(siameseFileEntity);
        if (!CollectionUtils.isEmpty(items))
            return new SiameseResult<>(ResponseEnum.REQUEST_ACCEPTED, items);
        else
            throw new SiameseException(ResponseEnum.RECORD_NOT_FOUND);
    }
}
