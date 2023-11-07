package com.example.demo.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.example.demo.config.TokenGenerator;

@Service
public class TokenService {

	private Map<String, String> emailTokenMapping = new HashMap<>();

	public String generateAndStoreToken(String email) {
		String token = TokenGenerator.generateToken();
		emailTokenMapping.put(email, token);
		return token;
	}

	public String getEmailForToken(String token) {
		for (Map.Entry<String, String> entry : emailTokenMapping.entrySet()) {
			if (entry.getValue().equals(token)) {
				return entry.getKey();
			}
		}
		return null; // Token not found
	}
}
