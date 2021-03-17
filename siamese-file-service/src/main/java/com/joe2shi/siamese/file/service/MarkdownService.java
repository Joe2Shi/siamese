package com.joe2shi.siamese.file.service;

import com.joe2shi.siamese.common.vo.SiameseResult;
import org.springframework.web.multipart.MultipartFile;

@SuppressWarnings("rawtypes")
public interface MarkdownService {
    /**
     * Upload markdown file
     *
     * @param file
     * @return
     */
    SiameseResult uploadMarkdown(MultipartFile file);

    /**
     * Delete markdown file
     *
     * @param id
     * @return
     */
    SiameseResult deleteMarkdown(String id);

    /**
     * Query all markdown
     *
     * @return
     */
    SiameseResult queryMarkdowns();
}
