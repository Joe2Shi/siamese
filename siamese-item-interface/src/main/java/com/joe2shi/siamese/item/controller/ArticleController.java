package com.joe2shi.siamese.item.controller;

import com.joe2shi.siamese.common.vo.SiameseResult;
import com.joe2shi.siamese.item.proxy.ArticleServiceProxy;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("article")
@SuppressWarnings("rawtypes")
public class ArticleController {
    @Resource
    private ArticleServiceProxy articleServiceProxy;

    @GetMapping("page")
    public ResponseEntity<SiameseResult> selectBrandByPage(
        @RequestParam(value = "key", required = false) String key,
        @RequestParam(value = "page", defaultValue = "1") Integer page,
        @RequestParam(value = "rows", defaultValue = "10") Integer rows,
        @RequestParam(value = "sortBy", required = false) String sortBy,
        @RequestParam(value = "desc", defaultValue = "false") Boolean desc
    ) {
        return ResponseEntity.ok(articleServiceProxy.selectBrandByPage(key, page, rows, sortBy, desc));
    }
}
