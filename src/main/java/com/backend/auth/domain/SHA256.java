package com.backend.auth.domain;

import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public abstract class SHA256 {

    public static String encrypt(String text){
        try{
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(text.getBytes(Charset.defaultCharset()));
            return bytesToHex(md.digest());
        }catch (NoSuchAlgorithmException e){
            throw new IllegalArgumentException("Failed to encrypt password");
        }
    }

    public static String bytesToHex(byte[] bytes){
        StringBuilder sb = new StringBuilder();

        for (byte b: bytes){
            sb.append(String.format("%02x", b)); // 숫자 16진수로 포맷
        }
        return sb.toString();
    }
}
