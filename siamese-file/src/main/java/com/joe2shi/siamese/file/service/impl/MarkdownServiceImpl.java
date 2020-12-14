package com.joe2shi.siamese.file.service.impl;

import com.joe2shi.siamese.file.service.MarkdownService;
import com.joe2shi.siamese.common.vo.BaseResult;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class MarkdownServiceImpl implements MarkdownService {

    @Override
    public BaseResult uploadMarkdown(MultipartFile file) {
        return null;
    }

}
