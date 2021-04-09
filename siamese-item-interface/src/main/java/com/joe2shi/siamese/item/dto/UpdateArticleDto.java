package com.joe2shi.siamese.item.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class UpdateArticleDto {
    private String id;
    private String title;
    private String subtitle;
    private String oldAddress;
    private MultipartFile file;
}
