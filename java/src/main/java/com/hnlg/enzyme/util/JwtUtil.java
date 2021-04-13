package com.hnlg.enzyme.util;

import io.jsonwebtoken.CompressionCodecs;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.UUID;

/**
 * jwt 工具类
 */
public class JwtUtil {
    private final static String key = "qieK4cua9YHNs98mztRin";

    /**
     * 加密
     *
     * @param body
     * @return
     */
    public static String encrypt(String body) {
        return Jwts.builder()
                .setSubject(body)
                .compressWith(CompressionCodecs.DEFLATE)
                .signWith(SignatureAlgorithm.HS512, key)
                .compact();
    }

    /**
     * 解密
     *
     * @param body
     * @return
     */
    public static String decrypt(String body) {
        return Jwts.parser().setSigningKey(key).parseClaimsJws(body).getBody().getSubject();
    }

    /**
     * 加密并获取之后的MD5
     *
     * @param body
     * @return
     */
    public static String encryptAndMd5(String body) {
        return MD5Util.getMD5(encrypt(body));
    }

    /**
     * 生成token
     */
    public static String generatorToken() {
        String uuid = UUID.randomUUID().toString();
        return MD5Util.getMD5(uuid);
    }
}
