package com.joe2shi.siamese.auth.config;

import com.joe2shi.siamese.common.utils.RsaUtils;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;

@ConfigurationProperties(prefix = "siamese.jwt")
@Data
@Slf4j
public class JwtProperties {
    private String secret;
    private String publicKeyPath;
    private String privateKeyPath;
    private PublicKey publicKey;
    private PrivateKey privateKey;

    @PostConstruct
    public void init() throws InvalidKeySpecException, NoSuchAlgorithmException, IOException {
        File pubKey = new File(publicKeyPath);
        File priKey = new File(privateKeyPath);
        if (!pubKey.exists() || !priKey.exists()) {
            RsaUtils.generateKey(publicKeyPath, privateKeyPath, secret);
        }
        this.publicKey = RsaUtils.getPublicKey(publicKeyPath);
        this.privateKey = RsaUtils.getPrivateKey(privateKeyPath);
    }
}
