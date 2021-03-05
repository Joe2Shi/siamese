package com.joe2shi.siamese.file.controller;

import com.joe2shi.siamese.common.vo.SiameseResult;
import com.joe2shi.siamese.file.proxy.MarkdownServiceProxy;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

@RestController
@RequestMapping("markdown")
@CrossOrigin
@SuppressWarnings("rawtypes")
public class MarkdownController {
    @Resource
    private MarkdownServiceProxy markdownServiceProxy;

    @PostMapping
    public ResponseEntity<SiameseResult> uploadMarkdown(@RequestPart("file") MultipartFile file) {
        return ResponseEntity.ok(markdownServiceProxy.uploadMarkdown(file));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<SiameseResult> deleteMarkdown(@PathVariable("id") String id) {
        return ResponseEntity.ok(markdownServiceProxy.deleteMarkdown(id));
    }

    @GetMapping("items")
    public ResponseEntity<SiameseResult> queryMarkdowns() {
        return ResponseEntity.ok(markdownServiceProxy.queryMarkdowns());
    }
}
