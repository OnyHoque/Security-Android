package com.hoque.securitypro;

import android.util.Log;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import static android.content.ContentValues.TAG;

public class AESUtils {
    private final static String HEX = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public static String encrypt(String cleartext, String secretKey) throws Exception {
        String sha1_clipped = secret_key_maker(secretKey);
        byte[] rawKey = sha1_clipped.getBytes("UTF-8");
        byte[] result = encrypt_mechanism(rawKey, cleartext.getBytes());
        return toHex(result);
    }

    public static String decrypt(String encrypted, String secretKey) throws Exception {
        String sha1_clipped = secret_key_maker(secretKey);
        byte[] enc = toByte(encrypted);
        byte[] result = decrypt_mechanism(enc, sha1_clipped);
        return new String(result);
    }


    private static byte[] encrypt_mechanism(byte[] raw, byte[] clear) throws Exception {
        SecretKey skeySpec = new SecretKeySpec(raw, "AES");
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
        byte[] encrypted = cipher.doFinal(clear);
        return encrypted;
    }

    private static byte[] decrypt_mechanism(byte[] encrypted, String secretKey) throws Exception {
        byte[] keyValue = secretKey.getBytes("UTF-8");
        SecretKey skeySpec = new SecretKeySpec(keyValue, "AES");
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, skeySpec);
        byte[] decrypted = cipher.doFinal(encrypted);
        return decrypted;
    }

    public static byte[] toByte(String hexString) {
        int len = hexString.length() / 2;
        byte[] result = new byte[len];
        for (int i = 0; i < len; i++)
            result[i] = Integer.valueOf(hexString.substring(2 * i, 2 * i + 2),
                    16).byteValue();
        return result;
    }

    public static String toHex(byte[] buf) {
        if (buf == null)
            return "";
        StringBuffer result = new StringBuffer(2 * buf.length);
        for (int i = 0; i < buf.length; i++) {
            appendHex(result, buf[i]);
        }
        return result.toString();
    }

    private static void appendHex(StringBuffer sb, byte b) {
        sb.append(HEX.charAt((b >> 4) & 0x0f)).append(HEX.charAt(b & 0x0f));
    }

    private static String convertToHex(byte[] data) {
        StringBuilder buf = new StringBuilder();
        for (byte b : data) {
            int halfbyte = (b >>> 4) & 0x0F;
            int two_halfs = 0;
            do {
                buf.append((0 <= halfbyte) && (halfbyte <= 9) ? (char) ('0' + halfbyte) : (char) ('a' + (halfbyte - 10)));
                halfbyte = b & 0x0F;
            } while (two_halfs++ < 1);
        }
        return buf.toString();
    }

    public static String SHA1(String text) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest md = MessageDigest.getInstance("SHA-1");
        md.update(text.getBytes("iso-8859-1"), 0, text.length());
        byte[] sha1hash = md.digest();
        return convertToHex(sha1hash);
    }

    public static String secret_key_maker(String str){
        String sha_clipped = "";
        try {
            String sha_str = SHA1(str);
            sha_clipped = sha_str.substring(0,16);
        } catch (Exception e) {
            Log.d(TAG, "secret_key: "+e);
        }
        return sha_clipped;
    }

    public static String String_to_SHA1(String str) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        return SHA1(str);
    }
}