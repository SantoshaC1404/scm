package com.scm.service.impl;

import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class TokenBlacklistService {
    private final Map<String, Instant> blacklist = new ConcurrentHashMap<>();

    public void blacklistToken(String token) {
        // store token with expiry time
        blacklist.put(token, Instant.now().plusSeconds(3600)); // 1 hr
    }

    public boolean isBlacklisted(String token) {
        Instant expiry = blacklist.get(token);
        if (expiry == null) return false;
        if (expiry.isBefore(Instant.now())) {
            blacklist.remove(token); // cleanup expired tokens
            return false;
        }
        return true;
    }
}
