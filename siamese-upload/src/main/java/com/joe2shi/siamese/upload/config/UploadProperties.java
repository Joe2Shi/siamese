package com.joe2shi.siamese.upload.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

@Data
@ConfigurationProperties(prefix = "siamese.upload")
public class UploadProperties {

    private String baseAddress;
    private List<String> allowTypes;

}
