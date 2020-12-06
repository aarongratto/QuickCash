package com.example.quickcash.password;
import java.nio.ByteBuffer;
import java.security.GeneralSecurityException;
import java.security.Key;
import java.security.KeyException;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.SecretKeySpec;

public class Encryptor {
    private static final String LOG_TAG = "Encryptor";

    /** length of key (byte) */
    private static final int KEY_LENGTH_BYTES = 16;

    /** length of key (bits) */
    private static final int KEY_LENGTH_BITS = KEY_LENGTH_BYTES * 8;

    /**
     *  create random key
     */
    public static Key generateKey() {
        try {
            KeyGenerator generator = KeyGenerator.getInstance("AES");
            SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
            generator.init(KEY_LENGTH_BITS, random);
            return generator.generateKey();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     *  convert byte arrayto key
     */
    public static Key getKey(byte[] bytes) {
        byte[] b = new byte[KEY_LENGTH_BYTES];
        for (int i = 0; i < KEY_LENGTH_BYTES; i++) {
            b[i] = i < bytes.length ? bytes[i] : 0; // シンプル：あいたところは0で埋める
        }
        return new SecretKeySpec(b, "AES");
    }

    public static byte[] encrypt(byte[] src, byte[] key) throws GeneralSecurityException {
        return encrypt(src, getKey(key));
    }

    public static byte[] decrypt(byte[] src, byte[] key) throws GeneralSecurityException {
        return decrypt(src, getKey(key));
    }

    public static byte[] encrypt(byte[] src, Key skey) throws GeneralSecurityException {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, skey);
        return cipher.doFinal(src);
    }

    public static byte[] decrypt(byte[] src, Key skey) throws GeneralSecurityException {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, skey);
        return cipher.doFinal(src);
    }
}
