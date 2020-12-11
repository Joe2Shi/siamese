package com.joe2shi.siamese.file.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

@Data
@ConfigurationProperties(prefix = "siamese.file")
public class FileProperties {

    private String baseAddress;
    private List<String> allowTypes;

}
