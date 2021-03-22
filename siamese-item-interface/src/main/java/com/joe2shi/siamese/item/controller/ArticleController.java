package com.joe2shi.siamese.item.controller;

import com.joe2shi.siamese.common.vo.SiameseResult;
import com.joe2shi.siamese.item.bo.InsertArticleBo;
import com.joe2shi.siamese.item.proxy.ArticleServiceProxy;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("article")
@SuppressWarnings("rawtypes")
public class ArticleController {
    @Resource
    private ArticleServiceProxy articleServiceProxy;

    @PostMapping
    public ResponseEntity<SiameseResult> insertArticle(@RequestBody InsertArticleBo insertArticle) {
        return ResponseEntity.ok(articleServiceProxy.insertArticle(insertArticle));
    }

    @GetMapping("page")
    public ResponseEntity<SiameseResult> selectArticleByPage(
        @RequestParam(value = "key", required = false) String key,
        @RequestParam(value = "page", defaultValue = "1") Integer page,
        @RequestParam(value = "rows", defaultValue = "10") Integer rows,
        @RequestParam(value = "sortBy", required = false) String sortBy,
        @RequestParam(value = "desc", defaultValue = "false") Boolean desc
    ) {
        return ResponseEntity.ok(articleServiceProxy.selectArticleByPage(key, page, rows, sortBy, desc));
    }

    @DeleteMapping
    public ResponseEntity<SiameseResult> deleteArticleByIds(@RequestParam("ids") List<String> ids) {
        return ResponseEntity.ok(articleServiceProxy.deleteArticleByIds(ids));
    }
}
