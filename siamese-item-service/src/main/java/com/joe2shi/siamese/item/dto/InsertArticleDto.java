package com.joe2shi.siamese.item.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class InsertArticleDto {
    private String title;
    private String subtitle;
    private MultipartFile file;
}
