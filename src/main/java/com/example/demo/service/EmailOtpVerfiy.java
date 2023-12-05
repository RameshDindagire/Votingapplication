package com.example.demo.service;

import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class EmailOtpVerfiy {

	 private static final int OTP_LENGTH = 6;
	    private static final int OTP_EXPIRATION_MINUTES = 1;

	    private final RedisTemplate<String, String> redisTemplate;

	    @Autowired
	    public EmailOtpVerfiy(RedisTemplate<String, String> redisTemplate) {
	        this.redisTemplate = redisTemplate;
	    }

	    public String generateOtp(String key) {
	        String otp = RandomStringUtils.randomNumeric(OTP_LENGTH);
	        redisTemplate.opsForValue().set(key, otp, OTP_EXPIRATION_MINUTES, TimeUnit.MINUTES);
	        return otp;
	    }

	    public boolean verifyOtp(String key, String otp) {
	        String cachedOtp = redisTemplate.opsForValue().get(key);
	        return otp.equals(cachedOtp);
	    }
}
