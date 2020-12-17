package com.joe2shi.siamese.file.controller;

import com.joe2shi.siamese.common.vo.BaseResult;
import com.joe2shi.siamese.file.entity.SiameseFileEntity;
import com.joe2shi.siamese.file.service.ImageService;
import com.joe2shi.siamese.common.vo.DataResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("image")
@CrossOrigin
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

    @GetMapping("images")
    public ResponseEntity<DataResult<List<SiameseFileEntity>>> queryAllImage() {
        return ResponseEntity.ok(imageService.queryAllImage());
    }

}
