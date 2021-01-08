package com.joe2shi.siamese.file.proxy.fallback;

import com.joe2shi.siamese.common.enums.ResponseEnum;
import com.joe2shi.siamese.common.vo.SiameseResult;
import com.joe2shi.siamese.file.proxy.MarkdownServiceProxy;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
@SuppressWarnings("rawtypes")
public class MarkdownServiceProxyHystrix implements MarkdownServiceProxy {
    @Override
    public SiameseResult uploadMarkdown(MultipartFile file) {
        return new SiameseResult(ResponseEnum.PLEASE_TRY_AGAIN_LATER);
    }

    @Override
    public SiameseResult deleteMarkdown(String id) {
        return new SiameseResult(ResponseEnum.PLEASE_TRY_AGAIN_LATER);
    }

    @Override
    public SiameseResult queryMarkdowns() {
        return new SiameseResult(ResponseEnum.PLEASE_TRY_AGAIN_LATER);
    }
}
