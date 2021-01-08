package com.joe2shi.siamese.file.proxy.fallback;

import com.joe2shi.siamese.common.enums.ResponseEnum;
import com.joe2shi.siamese.common.vo.SiameseResult;
import com.joe2shi.siamese.file.proxy.ImageServiceProxy;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
@SuppressWarnings("rawtypes")
public class ImageServiceProxyHystrix implements ImageServiceProxy {
    @Override
    public SiameseResult uploadImage(MultipartFile file) {
        return new SiameseResult(ResponseEnum.PLEASE_TRY_AGAIN_LATER);
    }

    @Override
    public SiameseResult deleteImage(String id) {
        return new SiameseResult(ResponseEnum.PLEASE_TRY_AGAIN_LATER);
    }

    @Override
    public SiameseResult queryImages() {
        return new SiameseResult(ResponseEnum.PLEASE_TRY_AGAIN_LATER);
    }
}
