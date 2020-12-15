package com.joe2shi.siamese.file.service;

import com.joe2shi.siamese.common.vo.BaseResult;
import org.springframework.web.multipart.MultipartFile;

public interface MarkdownService {

    /**
     * upload markdown file
     *
     * @param file
     * @return
     */
    BaseResult uploadMarkdown(MultipartFile file);

    /**
     * delete markdown file
     *
     * @param id
     * @return
     */
    BaseResult deleteMarkdown(String id);

}
