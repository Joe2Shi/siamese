package com.joe2shi.siamese.upload.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("upload")
public class UploadController {

    @PostMapping("image")
    public String uploadImage(@RequestParam("file") MultipartFile file) {
        return null;
    }

}
