package com.melo.vibyn.spotify.domain.port;

public interface TokenEncryptionPort {

    String encrypt(String plainText);
    String decrypt(String encryptedText);
}
