package com.joe2shi.siamese.file.service.impl;

import com.joe2shi.siamese.common.vo.SiameseResult;
import com.joe2shi.siamese.file.proxy.ImageServiceProxy;
import com.joe2shi.siamese.file.service.ImageService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

@Service
@SuppressWarnings("rawtypes")
public class ImageServiceImpl implements ImageService {
    @Resource
    private ImageServiceProxy imageServiceProxy;

    @Override
    public SiameseResult uploadImage(MultipartFile file) {
        return imageServiceProxy.uploadImage(file);
    }

    @Override
    public SiameseResult deleteImage(String id) {
        return imageServiceProxy.deleteImage(id);
    }

    @Override
    public SiameseResult queryAllImage() {
        return imageServiceProxy.queryAllImage();
    }
}
