package com.example.forum.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;

public class JwtUtil {

    private static final Key SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256); // 生成密钥
    private static final long EXPIRATION_TIME = 3600000; // 1小时有效期

    // 生成 Token
    public static String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username) // 设置用户名为 Token 的主题
                .setIssuedAt(new Date()) // 签发时间
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME)) // 有效期
                .signWith(SECRET_KEY) // 使用密钥签名
                .compact();
    }

    // 解析 Token，获取用户名
    public static String getUsernameFromToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY) // 设置签名密钥
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject(); // 返回 Token 中的主题
    }
}
