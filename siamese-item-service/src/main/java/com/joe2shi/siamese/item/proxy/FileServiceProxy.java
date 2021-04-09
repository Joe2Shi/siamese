package com.joe2shi.siamese.item.proxy;

import com.joe2shi.siamese.common.vo.SiameseResult;
import com.joe2shi.siamese.item.dto.DeleteFilesDto;
import com.joe2shi.siamese.item.proxy.fallback.FileServiceProxyHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

@FeignClient(name = "siamese-file", fallback = FileServiceProxyHystrix.class, path = "file")
@SuppressWarnings("rawtypes")
public interface FileServiceProxy {
    @PostMapping(value = "upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    SiameseResult uploadFile(@RequestPart("file") MultipartFile file, @RequestParam("type") String type);

    @PostMapping(value = "delete")
    SiameseResult deleteFiles(@RequestBody DeleteFilesDto deleteFiles);
}
