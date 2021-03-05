package com.joe2shi.siamese.auth.config;

import com.joe2shi.siamese.auth.utils.RsaUtils;
import com.joe2shi.siamese.common.constant.LoggerConstant;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;

import javax.annotation.PostConstruct;
import java.io.File;
import java.security.PrivateKey;
import java.security.PublicKey;

@ConfigurationProperties(prefix = "siamese.jwt")
@Data
@Slf4j
public class JwtProperties {
    private String secret;
    private String publicKeyPath;
    private String privateKeyPath;
    private Integer expire;
    private PublicKey publicKey;
    private PrivateKey privateKey;

    @PostConstruct
    public void init() {
        try {
            File pubKey = new File(publicKeyPath);
            File priKey = new File(privateKeyPath);
            if (!pubKey.exists() || !priKey.exists()) {
                RsaUtils.generateKey(publicKeyPath, privateKeyPath, secret);
            }
            this.publicKey = RsaUtils.getPublicKey(publicKeyPath);
            this.privateKey = RsaUtils.getPrivateKey(privateKeyPath);
        } catch (Exception e) {
            log.error(LoggerConstant.INIT_KEY_FAILED + e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
