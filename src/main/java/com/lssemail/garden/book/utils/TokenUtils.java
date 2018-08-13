package com.lssemail.garden.book.utils;

import com.alibaba.fastjson.JSON;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;

/**
 * Created by Administrator on 2018-8-6.
 */
public class TokenUtils {

    private static final String SECRET = "admin";
    private static final String ISSUER = "issuer";
    private static final String SUBJECT = "subject";
    private static long ttlMillis = 3600000;


    public static String createJwtToken(String id) {

        return createJwtToken(id, ISSUER, SUBJECT, ttlMillis);
    }

    /**
     *
     * @param id 自定义属性
     * @param issuer 签发者
     * @param subject 用户
     * @param ttlMillis 签发时间
     * @return
     */
    public static String createJwtToken(String id, String issuer, String subject, long ttlMillis){

        //step0:签名算法
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        //step1:签发时间
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        //step2:通过秘钥签名
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(SECRET);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());
        //step3
        JwtBuilder builder = Jwts.builder().setId(id)
                .setIssuer(issuer)
                .setIssuedAt(now)
                .setSubject(subject)
                .signWith(signatureAlgorithm, signingKey);

        //step4
        if(ttlMillis >= 0){
            long expMillis = nowMillis + ttlMillis;
            Date exp = new Date(expMillis);
            builder.setExpiration(exp);
        }

        return builder.compact();
    }

    public static Claims parseJWT(String jwt) {

        Claims claims = Jwts.parser()
                .setSigningKey(DatatypeConverter.parseBase64Binary(SECRET))
                .parseClaimsJws(jwt).getBody();
        return claims;
    }

    public static void main(String[] args) {

        String result = createJwtToken("liusir");
        Claims claims = parseJWT(result);
        System.out.println(JSON.toJSONString(claims));
        System.out.println(result);
    }


}
