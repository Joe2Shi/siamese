package com.joe2shi.siamese.file.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class UploadFileDto {
    private String type;
    private MultipartFile file;
}
