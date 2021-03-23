package com.joe2shi.siamese.gateway.config;

import com.joe2shi.siamese.common.utils.RsaUtils;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;

@ConfigurationProperties(prefix = "siamese.jwt")
@Data
@Slf4j
public class JwtProperties {
    private String publicKeyPath;
    private PublicKey publicKey;

    @PostConstruct
    public void init() throws InvalidKeySpecException, NoSuchAlgorithmException, IOException {
        this.publicKey = RsaUtils.getPublicKey(publicKeyPath);
    }
}
