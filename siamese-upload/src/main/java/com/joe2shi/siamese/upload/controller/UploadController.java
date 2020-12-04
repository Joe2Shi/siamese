package com.joe2shi.siamese.upload.controller;

import com.joe2shi.siamses.common.enums.ExceptionEnum;
import com.joe2shi.siamses.common.exception.SiameseException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("upload")
public class UploadController {

    @PostMapping("image")
    public String uploadImage(@RequestParam("file") MultipartFile file) {
        new SiameseException(ExceptionEnum.REQUEST_SUCCESS);
        return null;
    }

}
