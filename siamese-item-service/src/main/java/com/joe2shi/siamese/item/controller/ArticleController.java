package com.joe2shi.siamese.item.controller;

import com.joe2shi.siamese.common.constant.SystemConstant;
import com.joe2shi.siamese.common.vo.SiameseResult;
import com.joe2shi.siamese.item.dto.InsertArticleDto;
import com.joe2shi.siamese.item.dto.UpdateArticleDto;
import com.joe2shi.siamese.item.service.ArticleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("article")
@SuppressWarnings("rawtypes")
public class ArticleController {
    @Resource
    private ArticleService articleService;

    @PostMapping
    public ResponseEntity<SiameseResult> insertArticle(InsertArticleDto insertArticle, HttpServletRequest request) {
        String userId = request.getHeader(SystemConstant.STRING_ID);
        return ResponseEntity.ok(articleService.insertArticle(insertArticle, userId));
    }

    @PutMapping
    public ResponseEntity<SiameseResult> updateArticle(UpdateArticleDto updateArticle) {
        return ResponseEntity.ok(articleService.updateArticle(updateArticle));
    }

    @GetMapping("page")
    public ResponseEntity<SiameseResult> selectArticleByPage(
        @RequestParam(value = "key", required = false) String key,
        @RequestParam(value = "page", defaultValue = "1") Integer page,
        @RequestParam(value = "rows", defaultValue = "10") Integer rows,
        @RequestParam(value = "sortBy", required = false) String sortBy,
        @RequestParam(value = "desc", defaultValue = "false") Boolean desc,
        HttpServletRequest request
    ) {
        String userId = request.getHeader(SystemConstant.STRING_ID);
        return ResponseEntity.ok(articleService.selectArticleByPage(key, page, rows, sortBy, desc, userId));
    }

    @DeleteMapping()
    public ResponseEntity<SiameseResult> deleteArticleByIds(@RequestParam("ids") List<String> ids) {
        return ResponseEntity.ok(articleService.deleteArticleByIds(ids));
    }
}
