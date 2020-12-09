package com.joe2shi.siamese.upload.service;

import com.joe2shi.siamses.common.vo.DataResult;
import org.springframework.web.multipart.MultipartFile;

public interface UploadService {
    /**
     * upload image
     * @param file
     * @return
     */
    DataResult<String> uploadImage(MultipartFile file);

}
