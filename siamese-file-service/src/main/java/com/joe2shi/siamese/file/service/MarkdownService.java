package com.joe2shi.siamese.file.service;

import com.joe2shi.siamese.common.vo.SiameseResult;
import org.springframework.web.multipart.MultipartFile;

@SuppressWarnings("rawtypes")
public interface MarkdownService {
    /**
     * upload markdown file
     *
     * @param file
     * @return
     */
    SiameseResult uploadMarkdown(MultipartFile file);

    /**
     * delete markdown file
     *
     * @param id
     * @return
     */
    SiameseResult deleteMarkdown(String id);
}
