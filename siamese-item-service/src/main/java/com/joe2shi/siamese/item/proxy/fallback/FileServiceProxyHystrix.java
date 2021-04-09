package com.joe2shi.siamese.item.proxy.fallback;

import com.joe2shi.siamese.common.enums.ResponseEnum;
import com.joe2shi.siamese.common.exception.SiameseException;
import com.joe2shi.siamese.common.vo.SiameseResult;
import com.joe2shi.siamese.item.dto.DeleteFilesDto;
import com.joe2shi.siamese.item.proxy.FileServiceProxy;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
@SuppressWarnings("rawtypes")
public class FileServiceProxyHystrix implements FileServiceProxy {
    @Override
    public SiameseResult uploadFile(MultipartFile file, String type) {
        throw new SiameseException(ResponseEnum.PLEASE_TRY_AGAIN_LATER);
    }

    @Override
    public SiameseResult deleteFiles(DeleteFilesDto deleteFiles) {
        throw new SiameseException(ResponseEnum.PLEASE_TRY_AGAIN_LATER);
    }
}
