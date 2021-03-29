package com.joe2shi.siamese.txmanage.controller;

import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import com.joe2shi.siamese.common.constant.SystemConstant;
import com.joe2shi.siamese.common.enums.ResponseEnum;
import com.joe2shi.siamese.common.exception.SiameseException;
import com.joe2shi.siamese.common.vo.SiameseResult;
import com.joe2shi.siamese.txmanage.bo.InsertArticleBo;
import com.joe2shi.siamese.txmanage.proxy.ArticleServiceProxy;
import com.joe2shi.siamese.txmanage.proxy.UserServiceProxy;
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

    @Resource
    private UserServiceProxy userServiceProxy;

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

    @GetMapping("tx-manage")
    @LcnTransaction
    public ResponseEntity<SiameseResult> txManage() {
        SiameseResult articleResult = articleServiceProxy.txManage();
        if (SystemConstant.SUCCESS_CODE != articleResult.getCode()) {
            throw new SiameseException(ResponseEnum.ADD_ARTICLE_FAILED);
        }
        SiameseResult userResult = userServiceProxy.txManage();
        if (SystemConstant.SUCCESS_CODE != userResult.getCode()) {
            throw new SiameseException(ResponseEnum.ADD_ARTICLE_FAILED);
        }
        return ResponseEntity.ok(new SiameseResult(ResponseEnum.OPERATING_SUCCESS));
    }
}
