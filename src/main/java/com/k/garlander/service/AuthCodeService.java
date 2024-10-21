package com.k.garlander.service;

import com.k.garlander.security.encode.SHA256;
import com.k.garlander.support.RandomCodeGenerator;
import com.k.garlander.support.ResourceLoader;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthCodeService {

    private final EmailService emailService;
    private final SHA256 sha256;
    private final RedisService redisService;

    private static final int EXPIRATION_TIME = 5;

    public void sendAuthCode(String email)
            throws IOException, NoSuchAlgorithmException {
        String authCode = RandomCodeGenerator.generateAuthCode();
        redisService.setOnRedisForAuthCode(email, sha256.encode(authCode), EXPIRATION_TIME);
        emailService.sendMail(email, ResourceLoader.loadEmailHtml(email, authCode, EXPIRATION_TIME));
    }

    public void verifyAuthCode(String email, String authCode) throws NoSuchAlgorithmException {
        String storedCode = redisService.getOnRedisForAuthCode(email);

        if (storedCode == null || !sha256.matches(authCode, storedCode)) {
            throw new RuntimeException("코드가 일치하지 않습니다.");
        }
        redisService.deleteOnRedisForAuthenticEmail(email);
        redisService.setOnRedisForAuthenticEmail(email, EXPIRATION_TIME);
    }
}