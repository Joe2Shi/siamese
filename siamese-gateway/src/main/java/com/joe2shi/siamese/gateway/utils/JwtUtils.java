package com.joe2shi.siamese.gateway.utils;

import com.joe2shi.siamese.common.constant.SystemConstant;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.security.PrivateKey;
import java.security.PublicKey;

public class JwtUtils {

    /**
     * 私钥加密token
     *
     * @param userInfo   载荷中的数据
     * @param privateKey 私钥
     * @return
     */
    public static String generateToken(UserInfo userInfo, PrivateKey privateKey) {
        return Jwts.builder()
            .claim(SystemConstant.STRING_ID, userInfo.getId())
            .claim(SystemConstant.STRING_TIMESTAMP, userInfo.getTimestamp())
            .signWith(SignatureAlgorithm.RS256, privateKey)
            .compact();
    }

    /**
     * 私钥加密token
     *
     * @param userInfo   载荷中的数据
     * @param privateKey 私钥字节数组
     * @return
     * @throws Exception
     */
    public static String generateToken(UserInfo userInfo, byte[] privateKey) throws Exception {
        return Jwts.builder()
            .claim(SystemConstant.STRING_ID, userInfo.getId())
            .claim(SystemConstant.STRING_TIMESTAMP, userInfo.getTimestamp())
            .signWith(SignatureAlgorithm.RS256, RsaUtils.getPrivateKey(privateKey))
            .compact();
    }

    /**
     * 公钥解析token
     *
     * @param token     用户请求中的token
     * @param publicKey 公钥
     * @return
     */
    private static Jws<Claims> parserToken(String token, PublicKey publicKey) {
        return Jwts.parser().setSigningKey(publicKey).parseClaimsJws(token);
    }

    /**
     * 公钥解析token
     *
     * @param token     用户请求中的token
     * @param publicKey 公钥字节数组
     * @return
     * @throws Exception
     */
    private static Jws<Claims> parserToken(String token, byte[] publicKey) throws Exception {
        return Jwts.parser().setSigningKey(RsaUtils.getPublicKey(publicKey))
            .parseClaimsJws(token);
    }

    /**
     * 获取token中的用户信息
     *
     * @param token     用户请求中的令牌
     * @param publicKey 公钥
     * @return 用户信息
     */
    public static UserInfo getInfoFromToken(String token, PublicKey publicKey) {
        Jws<Claims> claimsJws = parserToken(token, publicKey);
        Claims body = claimsJws.getBody();
        return new UserInfo(ObjectUtils.toString(body.get(SystemConstant.STRING_ID)), ObjectUtils.toLong(body.get(SystemConstant.STRING_TIMESTAMP)));
    }

    /**
     * 获取token中的用户信息
     *
     * @param token     用户请求中的令牌
     * @param publicKey 公钥
     * @return 用户信息
     * @throws Exception
     */
    public static UserInfo getInfoFromToken(String token, byte[] publicKey) throws Exception {
        Jws<Claims> claimsJws = parserToken(token, publicKey);
        Claims body = claimsJws.getBody();
        return new UserInfo(ObjectUtils.toString(body.get(SystemConstant.STRING_ID)), ObjectUtils.toLong(body.get(SystemConstant.STRING_TIMESTAMP)));
    }
}
