package com.joe2shi.siamese.gateway.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

@ConfigurationProperties(prefix = "siamese.filter")
@Data
public class FilterProperties {
    private List<String> allowPaths;
}
