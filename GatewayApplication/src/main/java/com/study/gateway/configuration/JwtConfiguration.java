package com.study.gateway.configuration;

import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

@Component
//@ConfigurationProperties("token.jwt")
public class JwtConfiguration {
    private String key;
    //在哪里生成iss
    private String iss;
    //有效期 单位：分钟
    private int expiredMinute;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getIss() {
        return iss;
    }

    public void setIss(String iss) {
        this.iss = iss;
    }

    public int getExpiredMinute() {
        return expiredMinute;
    }

    public void setExpiredMinute(int expiredMinute) {
        this.expiredMinute = expiredMinute;
    }

    public SecretKey getSecretKey() {
        return new SecretKeySpec(this.getKey().getBytes(), SignatureAlgorithm.HS512.getJcaName());
    }
}
