package com.joe2shi.siamese.file.dto;

import lombok.Data;

import java.util.List;

@Data
public class DeleteFilesDto {
    private List<String> addresses;
}
