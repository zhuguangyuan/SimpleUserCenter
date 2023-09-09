package com.bruce.auth.utils;

import com.bruce.auth.exceptions.AuthException;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;

@Component
public class CryptUtils {
    private static final String cryptAlgo = "SHA-256";

    public static String encrypt(String str, String salt) {
        if (str == null) {
            str = "";
        }

        MessageDigest digest;
        try {
            digest = MessageDigest.getInstance(cryptAlgo);
        } catch (NoSuchAlgorithmException e) {
            throw new AuthException(e.getMessage());
        }

        byte[] encodeHash = digest.digest(
                str.concat(salt).getBytes(StandardCharsets.UTF_8));
        return bytesToHex(encodeHash);
    }

    private static String bytesToHex(byte[] hash) {
        StringBuilder hexString = new StringBuilder(2 * hash.length);
        for (byte b : hash) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }

    /**
     * generate salt with length of n
     *
     * @param n length of the target salt
     * @return salt
     */
    public static String generateSalt(int n) {
        char[] chars = "ABCDEFGHIJKLMNOPORSTUMXYZabcdefghiiklmnopgrstuvwxyz0123456789!@#$%&*)".toCharArray();
        StringBuilder sb = new StringBuilder();
        SecureRandom secureRandom = new SecureRandom();
        for (int i = 0; i < n; i++) {
            char aChar = chars[secureRandom.nextInt(chars.length)];
            sb.append(aChar);
        }
        return sb.toString();
    }
}
