package com.joe2shi.siamese.item.proxy;

import com.joe2shi.siamese.common.vo.SiameseResult;
import com.joe2shi.siamese.item.proxy.impl.ItemServiceProxyHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@FeignClient(name = "siamese-item-service", fallback = ItemServiceProxyHystrix.class, path = "article")
@SuppressWarnings("rawtypes")
public interface ItemServiceProxy {
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    SiameseResult insertArticle(
        @RequestParam("title") String title,
        @RequestParam("subtitle") String subtitle,
        @RequestPart("file") MultipartFile file
    );

    @PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    SiameseResult updateArticle(
        @RequestParam("id") String id,
        @RequestParam("title") String title,
        @RequestParam("subtitle") String subtitle,
        @RequestParam("oldAddress") String oldAddress,
        @RequestPart("file") MultipartFile file
    );

    @GetMapping("page")
    SiameseResult selectArticleByPage(
        @RequestParam(value = "key", required = false) String key,
        @RequestParam(value = "page", defaultValue = "1") Integer page,
        @RequestParam(value = "rows", defaultValue = "10") Integer rows,
        @RequestParam(value = "sortBy", required = false) String sortBy,
        @RequestParam(value = "desc", defaultValue = "false") Boolean desc
    );

    @DeleteMapping
    SiameseResult deleteArticleByIds(@RequestParam("ids") List<String> ids);
}
