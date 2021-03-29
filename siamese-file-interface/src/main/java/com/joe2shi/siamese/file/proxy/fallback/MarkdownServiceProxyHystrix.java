package com.joe2shi.siamese.file.proxy.fallback;

import com.joe2shi.siamese.common.enums.ResponseEnum;
import com.joe2shi.siamese.common.exception.SiameseException;
import com.joe2shi.siamese.common.vo.SiameseResult;
import com.joe2shi.siamese.file.proxy.MarkdownServiceProxy;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
@SuppressWarnings("rawtypes")
public class MarkdownServiceProxyHystrix implements MarkdownServiceProxy {
    @Override
    public SiameseResult uploadMarkdown(MultipartFile file) {
        throw new SiameseException(ResponseEnum.PLEASE_TRY_AGAIN_LATER);
    }

    @Override
    public SiameseResult deleteMarkdown(String id) {
        throw new SiameseException(ResponseEnum.PLEASE_TRY_AGAIN_LATER);
    }

    @Override
    public SiameseResult queryMarkdowns() {
        throw new SiameseException(ResponseEnum.PLEASE_TRY_AGAIN_LATER);
    }
}
