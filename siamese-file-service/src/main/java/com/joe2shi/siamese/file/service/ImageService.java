package com.joe2shi.siamese.file.service;

import com.joe2shi.siamese.common.vo.SiameseResult;
import org.springframework.web.multipart.MultipartFile;

@SuppressWarnings("rawtypes")
public interface ImageService {
    /**
     * Upload image
     *
     * @param file
     * @return
     */
    SiameseResult uploadImage(MultipartFile file);

    /**
     * Delete image
     *
     * @param id
     * @return
     */
    SiameseResult deleteImage(String id);

    /**
     * Query all image
     *
     * @return
     */
    SiameseResult queryImages();
}
