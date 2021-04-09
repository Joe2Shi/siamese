package com.joe2shi.siamese.item.dto;

import lombok.Data;

import java.util.List;

@Data
public class DeleteFilesDto {
    private List<String> addresses;
}
