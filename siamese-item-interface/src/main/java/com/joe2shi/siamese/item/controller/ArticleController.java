package com.joe2shi.siamese.item.controller;

import com.joe2shi.siamese.common.vo.SiameseResult;
import com.joe2shi.siamese.item.dto.InsertArticleDto;
import com.joe2shi.siamese.item.dto.UpdateArticleDto;
import com.joe2shi.siamese.item.proxy.ItemServiceProxy;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("article")
@SuppressWarnings("rawtypes")
public class ArticleController {
    @Resource
    private ItemServiceProxy itemServiceProxy;

    @PostMapping
    public ResponseEntity<SiameseResult> insertArticle(InsertArticleDto insertArticle) {
        return ResponseEntity.ok(itemServiceProxy.insertArticle(
            insertArticle.getTitle(),
            insertArticle.getSubtitle(),
            insertArticle.getFile()
        ));
    }

    @PutMapping
    public ResponseEntity<SiameseResult> updateArticle(UpdateArticleDto updateArticle) {
        return ResponseEntity.ok(itemServiceProxy.updateArticle(
            updateArticle.getId(),
            updateArticle.getTitle(),
            updateArticle.getSubtitle(),
            updateArticle.getOldAddress(),
            updateArticle.getFile()
        ));
    }

    @GetMapping("page")
    public ResponseEntity<SiameseResult> selectArticleByPage(
        @RequestParam(value = "key", required = false) String key,
        @RequestParam(value = "page", defaultValue = "1") Integer page,
        @RequestParam(value = "rows", defaultValue = "10") Integer rows,
        @RequestParam(value = "sortBy", required = false) String sortBy,
        @RequestParam(value = "desc", defaultValue = "false") Boolean desc
    ) {
        return ResponseEntity.ok(itemServiceProxy.selectArticleByPage(key, page, rows, sortBy, desc));
    }

    @DeleteMapping
    public ResponseEntity<SiameseResult> deleteArticleByIds(@RequestParam("ids") List<String> ids) {
        return ResponseEntity.ok(itemServiceProxy.deleteArticleByIds(ids));
    }
}
