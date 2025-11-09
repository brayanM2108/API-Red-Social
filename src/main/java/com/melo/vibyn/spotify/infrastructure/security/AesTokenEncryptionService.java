package com.melo.vibyn.spotify.infrastructure.security;


import com.melo.vibyn.spotify.domain.port.TokenEncryptionPort;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

@Component
public class AesTokenEncryptionService implements TokenEncryptionPort {

    private final SecretKeySpec secretKey;
    private static final String ALGORITHM = "AES";

    public AesTokenEncryptionService(@Value("${spotify.encryption.key}") String key) {
        this.secretKey = new SecretKeySpec(key.getBytes(), ALGORITHM);
    }

    @Override
    public String encrypt(String plainText) {
        try {
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            return Base64.getEncoder().encodeToString(cipher.doFinal(plainText.getBytes()));
        } catch (Exception e) {
            throw new RuntimeException("Error encrypting token", e);
        }
    }

    @Override
    public String decrypt(String encryptedText) {
        try {
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            return new String(cipher.doFinal(Base64.getDecoder().decode(encryptedText)));
        } catch (Exception e) {
            throw new RuntimeException("Error decrypting token", e);
        }
    }
}
