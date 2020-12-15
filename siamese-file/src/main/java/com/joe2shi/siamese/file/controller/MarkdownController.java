package com.joe2shi.siamese.file.controller;

import com.joe2shi.siamese.file.service.MarkdownService;
import com.joe2shi.siamese.common.vo.BaseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("markdown")
public class MarkdownController {

    @Autowired
    private MarkdownService markdownService;

    @PostMapping
    public ResponseEntity<BaseResult> uploadMarkdown(@RequestParam("file") MultipartFile file) {
        return ResponseEntity.ok(markdownService.uploadMarkdown(file));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<BaseResult> deleteMarkdown(@PathVariable("id") String id) {
        return ResponseEntity.ok(markdownService.deleteMarkdown(id));
    }

}
