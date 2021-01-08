package com.joe2shi.siamese.file.service.impl;

import com.joe2shi.siamese.common.vo.SiameseResult;
import com.joe2shi.siamese.file.proxy.MarkdownServiceProxy;
import com.joe2shi.siamese.file.service.MarkdownService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;


@Service
@SuppressWarnings("rawtypes")
public class MarkdownServiceImpl implements MarkdownService {
    @Resource
    private MarkdownServiceProxy markdownServiceProxy;

    @Override
    public SiameseResult uploadMarkdown(MultipartFile file) {
        return markdownServiceProxy.uploadMarkdown(file);
    }

    @Override
    public SiameseResult deleteMarkdown(String id) {
        return markdownServiceProxy.deleteMarkdown(id);
    }

    @Override
    public SiameseResult queryMarkdowns() {
        return markdownServiceProxy.queryMarkdowns();
    }
}
