package com.joe2shi.siamese.file.service;

import com.joe2shi.siamese.common.vo.BaseResult;
import com.joe2shi.siamese.common.vo.DataResult;
import org.springframework.web.multipart.MultipartFile;

public interface ImageService {

    /**
     * upload image
     *
     * @param file
     * @return
     */
    DataResult<String> uploadImage(MultipartFile file);

    /**
     * delete image
     *
     * @param id
     * @return
     */
    BaseResult deleteImage(String id);
}
