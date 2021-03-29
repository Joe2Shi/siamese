package com.joe2shi.siamese.txmanage.controller;

import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import com.joe2shi.siamese.common.vo.SiameseResult;
import com.joe2shi.siamese.txmanage.bo.InsertArticleBo;
import com.joe2shi.siamese.txmanage.service.ArticleService;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("article")
@SuppressWarnings("rawtypes")
public class ArticleController {
    @Resource
    private ArticleService articleService;

    @PostMapping
    public ResponseEntity<SiameseResult> insertArticle(@RequestBody InsertArticleBo insertArticle) {
        return ResponseEntity.ok(articleService.insertArticle(insertArticle));
    }

    @GetMapping("page")
    public ResponseEntity<SiameseResult> selectArticleByPage(
        @RequestParam(value = "key", required = false) String key,
        @RequestParam(value = "page", defaultValue = "1") Integer page,
        @RequestParam(value = "rows", defaultValue = "10") Integer rows,
        @RequestParam(value = "sortBy", required = false) String sortBy,
        @RequestParam(value = "desc", defaultValue = "false") Boolean desc
    ) {
        return ResponseEntity.ok(articleService.selectArticleByPage(key, page, rows, sortBy, desc));
    }

    @DeleteMapping()
    public ResponseEntity<SiameseResult> deleteArticleByIds(@RequestParam("ids") List<String> ids) {
        return ResponseEntity.ok(articleService.deleteArticleByIds(ids));
    }

    @GetMapping("tx-manage")
    @LcnTransaction
    @Transactional
    public ResponseEntity<SiameseResult> txManage() {
        InsertArticleBo insertArticle = new InsertArticleBo();
        insertArticle.setTitle("Java");
        insertArticle.setSubtitle("Java基础");
        insertArticle.setUrl("http://www.joe2shi.com");
        return ResponseEntity.ok(articleService.insertArticle(insertArticle));
    }
}
