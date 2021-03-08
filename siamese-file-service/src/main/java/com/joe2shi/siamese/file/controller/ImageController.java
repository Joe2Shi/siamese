package com.joe2shi.siamese.file.controller;

import com.joe2shi.siamese.file.service.ImageService;
import com.joe2shi.siamese.common.vo.SiameseResult;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

@RestController
@RequestMapping("image")
@SuppressWarnings("rawtypes")
public class ImageController {
    @Resource
    private ImageService imageService;

    @PostMapping
    public ResponseEntity<SiameseResult> uploadImage(@RequestPart("file") MultipartFile file) {
        return ResponseEntity.ok(imageService.uploadImage(file));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<SiameseResult> deleteImage(@PathVariable("id") String id) {
        return ResponseEntity.ok(imageService.deleteImage(id));
    }

    @GetMapping("items")
    public ResponseEntity<SiameseResult> queryImages() {
        return ResponseEntity.ok(imageService.queryImages());
    }
}
