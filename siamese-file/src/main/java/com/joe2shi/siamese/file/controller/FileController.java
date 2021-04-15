package com.joe2shi.siamese.file.controller;

import com.joe2shi.siamese.common.vo.SiameseResult;
import com.joe2shi.siamese.file.dto.DeleteFilesDto;
import com.joe2shi.siamese.file.dto.ReadTextFileDto;
import com.joe2shi.siamese.file.dto.UploadFileDto;
import com.joe2shi.siamese.file.service.FileService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("file")
@SuppressWarnings("rawtypes")
public class FileController {
    @Resource
    private FileService fileService;

    @PostMapping("upload")
    public ResponseEntity<SiameseResult> uploadFile(UploadFileDto uploadFile) {
        return ResponseEntity.ok(fileService.uploadFile(uploadFile));
    }

    @PostMapping("delete")
    public ResponseEntity<SiameseResult> deleteFiles(@RequestBody DeleteFilesDto deleteFiles) {
        return ResponseEntity.ok(fileService.deleteFiles(deleteFiles.getAddresses()));
    }

    @PostMapping("read")
    public ResponseEntity<SiameseResult> readTextFile(@RequestBody ReadTextFileDto readTextFile) {
        return ResponseEntity.ok(fileService.readTextFile(readTextFile));
    }
}
