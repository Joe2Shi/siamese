package com.joe2shi.siamese.gateway.config;

import com.joe2shi.siamese.common.constant.LoggerConstant;
import com.joe2shi.siamese.gateway.utils.RsaUtils;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;

import javax.annotation.PostConstruct;
import java.security.PublicKey;

@ConfigurationProperties(prefix = "siamese.jwt")
@Data
@Slf4j
public class JwtProperties {
    private String publicKeyPath;
    private PublicKey publicKey;

    @PostConstruct
    public void init() {
        try {

            this.publicKey = RsaUtils.getPublicKey(publicKeyPath);
        } catch (Exception e) {
            log.error(LoggerConstant.INIT_KEY_FAILED + e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
