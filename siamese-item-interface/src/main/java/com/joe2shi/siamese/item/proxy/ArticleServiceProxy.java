package com.joe2shi.siamese.item.proxy;

import com.joe2shi.siamese.common.vo.SiameseResult;
import com.joe2shi.siamese.item.proxy.impl.ArticleServiceProxyHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "siamese-item-service", fallback = ArticleServiceProxyHystrix.class, path = "article")
@SuppressWarnings("rawtypes")
public interface ArticleServiceProxy {
    @GetMapping("page")
    SiameseResult selectBrandByPage(
        @RequestParam(value = "key", required = false) String key,
        @RequestParam(value = "page", defaultValue = "1") Integer page,
        @RequestParam(value = "rows", defaultValue = "10") Integer rows,
        @RequestParam(value = "sortBy", required = false) String sortBy,
        @RequestParam(value = "desc", defaultValue = "false") Boolean desc
    );

    @DeleteMapping()
    SiameseResult deleteByIds(@RequestParam("ids") List<String> ids);
}
