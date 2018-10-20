package com.ifreedomer.cplus.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public final class MD5Util {
    private static final char[] hexDigits = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    private MD5Util() {
    }

    public static String toHexString(byte[] bytes) {
        if (bytes == null) {
            return "";
        }
        StringBuilder hex = new StringBuilder(bytes.length * 2);
        for (byte b : bytes) {
            hex.append(hexDigits[(b >> 4) & 15]);
            hex.append(hexDigits[b & 15]);
        }
        return hex.toString();
    }



    public static String md5(String string) {
        try {
            return toHexString(MessageDigest.getInstance("MD5").digest(string.getBytes("UTF-8")));
        } catch (NoSuchAlgorithmException neverHappened) {
            throw new RuntimeException(neverHappened);
        } catch (UnsupportedEncodingException neverHappened2) {
            throw new RuntimeException(neverHappened2);
        }
    }
}