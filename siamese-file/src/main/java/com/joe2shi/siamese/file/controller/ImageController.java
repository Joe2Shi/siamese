package com.joe2shi.siamese.file.controller;

import com.joe2shi.siamese.common.vo.BaseResult;
import com.joe2shi.siamese.file.service.ImageService;
import com.joe2shi.siamese.common.vo.DataResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("image")
public class ImageController {

    @Autowired
    private ImageService imageService;

    @PostMapping
    public ResponseEntity<DataResult<String>> uploadImage(@RequestParam("file") MultipartFile file) {
        return ResponseEntity.ok(imageService.uploadImage(file));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<BaseResult> deleteImage(@PathVariable("id") String id) {
        return ResponseEntity.ok(imageService.deleteImage(id));
    }

}
