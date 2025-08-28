package cn.edu.guet.login.util;

import cn.edu.guet.login.domain.vo.TokenVO;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.access.expire}")
    private Long accessExpire;

    @Value("${jwt.refresh.expire}")
    private Long refreshExpire;

    // 获取签名密钥（确保密钥长度符合要求）
    private Key getSigningKey() {
        // 检查密钥长度，如果不足则抛出明确异常
        byte[] keyBytes = secret.getBytes();
        if (keyBytes.length < 32) { // 32字节 = 256位
            throw new IllegalArgumentException("JWT密钥长度必须至少为256位（32个字符）");
        }
        return Keys.hmacShaKeyFor(keyBytes);
    }

    private String generateToken(Map<String, Object> claims, Long expireSeconds) {
        Date now = new Date();
        Date expireDate = new Date(now.getTime() + expireSeconds * 1000);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(expireDate)
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    private Claims parseToken(String token) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(getSigningKey())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            throw new RuntimeException("Token 解析失败: " + e.getMessage(), e);
        }
    }

    public String generateAccessToken(Integer userId) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", userId);
        claims.put("tokenType", "access");
        return generateToken(claims, accessExpire);
    }

    public String generateRefreshToken(Integer userId) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", userId);
        claims.put("tokenType", "refresh");
        return generateToken(claims, refreshExpire);
    }

    public boolean validateAccessToken(String token) {
        try {
            Claims claims = parseToken(token);
            return !claims.getExpiration().before(new Date())
                    && "access".equals(claims.get("tokenType"));
        } catch (Exception e) {
            return false;
        }
    }

    public boolean validateRefreshToken(String token) {
        try {
            Claims claims = parseToken(token);
            return !claims.getExpiration().before(new Date())
                    && "refresh".equals(claims.get("tokenType"));
        } catch (Exception e) {
            return false;
        }
    }

    public Integer getUserIdFromToken(String token) {
        Claims claims = parseToken(token);
        return claims.get("userId", Integer.class);
    }
}
