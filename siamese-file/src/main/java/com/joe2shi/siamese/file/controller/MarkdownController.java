package com.joe2shi.siamese.file.controller;

import com.joe2shi.siamese.file.service.MarkdownService;
import com.joe2shi.siamses.common.vo.BaseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("markdown")
public class MarkdownController {

    @Autowired
    private MarkdownService markdownService;

    @PostMapping("upload")
    public ResponseEntity<BaseResult> uploadMarkdown(@RequestParam("file") MultipartFile file) {
        return ResponseEntity.ok(markdownService.uploadMarkdown(file));
    }

}
