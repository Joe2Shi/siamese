package com.joe2shi.siamese.file.service;

import com.joe2shi.siamese.common.vo.SiameseResult;
import com.joe2shi.siamese.file.dto.UploadFileDto;

import java.util.List;

@SuppressWarnings("rawtypes")
public interface FileService {
    /**
     * Upload image
     *
     * @param uploadFile
     * @return
     */
    SiameseResult uploadFile(UploadFileDto uploadFile);

    /**
     * Delete image
     *
     * @param addresses
     * @return
     */
    SiameseResult deleteFiles(List<String> addresses);
}
