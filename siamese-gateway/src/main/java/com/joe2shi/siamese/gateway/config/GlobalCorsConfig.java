package com.joe2shi.siamese.gateway.config;

import com.joe2shi.siamese.common.constant.SystemConstant;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.List;

@Data
@Configuration
@ConfigurationProperties(prefix = "siamese.domain")
public class GlobalCorsConfig {
    private List<String> allowDomains;

    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration config = new CorsConfiguration();
        for (String domain : allowDomains) {
            config.addAllowedOrigin(domain);
        }
        config.setAllowCredentials(Boolean.TRUE);
        config.addAllowedMethod(HttpMethod.OPTIONS);
        config.addAllowedMethod(HttpMethod.HEAD);
        config.addAllowedMethod(HttpMethod.GET);
        config.addAllowedMethod(HttpMethod.PUT);
        config.addAllowedMethod(HttpMethod.POST);
        config.addAllowedMethod(HttpMethod.DELETE);
        config.addAllowedMethod(HttpMethod.PATCH);
        config.addAllowedHeader(SystemConstant.CHARACTER_ASTERISK);
        UrlBasedCorsConfigurationSource configSource = new UrlBasedCorsConfigurationSource();
        configSource.registerCorsConfiguration(SystemConstant.STRING_MATCH_ALL_PATH, config);
        return new CorsFilter(configSource);
    }
}
