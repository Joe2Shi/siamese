package com.joe2shi.siamese.file.proxy;

import com.joe2shi.siamese.common.vo.SiameseResult;
import com.joe2shi.siamese.file.proxy.fallback.ImageServiceProxyHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@FeignClient(name="siamese-file-service", fallback = ImageServiceProxyHystrix.class, path = "/image", contextId = "image")
@SuppressWarnings("rawtypes")
public interface ImageServiceProxy {
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    SiameseResult uploadImage(@RequestPart("file") MultipartFile file);

    @DeleteMapping("{id}")
    SiameseResult deleteImage(@PathVariable("id") String id);

    @GetMapping("images")
    SiameseResult queryAllImage();
}
