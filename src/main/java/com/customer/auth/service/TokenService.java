package com.customer.auth.service;

import java.time.Duration;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class TokenService {

    private final RedisTemplate<String, Object> redisTemplate;

    public TokenService(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void saveToken(String token, String username) {
        redisTemplate.opsForValue().set(token, username, Duration.ofMinutes(30)); // Set token expiration time
    }

    public void saveRefreshToken(String refToken, String username) {
        redisTemplate.opsForValue().set(refToken, username, Duration.ofHours(8)); // Set token expiration time
    }
    
    public String getUsernameByToken(String token) {
        return (String) redisTemplate.opsForValue().get(token);
    }
    
    public boolean isRefTokenValid(String refToken) {
    	return (String) redisTemplate.opsForValue().get(refToken) != null;
    }
    
    public void invalidateToken(String token) {
    	redisTemplate.opsForValue().getAndDelete(token);
    }
    
    public void invalidateRefreshToken(String refToken) {
    	redisTemplate.opsForValue().getAndDelete(refToken);
    }
}
