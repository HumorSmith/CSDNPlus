package com.ifreedomer.cplus.util;

import android.text.TextUtils;
import android.util.Base64;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class SecurityUtil {
    private static byte[] aes_key = "123456789012.csdn.mobile".getBytes();

    public static String DESEncrypt(String plainText) {
        try {
            if (TextUtils.isEmpty(plainText)) {
                return "";
            }
            SecretKey deskey = new SecretKeySpec(aes_key, "DESede");
            Cipher cipher = Cipher.getInstance("DESede");
            cipher.init(1, deskey);
            return new String(Base64.encode(cipher.doFinal(plainText.getBytes("UTF-8")), 2), "UTF-8");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return "";
        } catch (NoSuchPaddingException e2) {
            e2.printStackTrace();
            return "";
        } catch (InvalidKeyException e3) {
            e3.printStackTrace();
            return "";
        } catch (IllegalBlockSizeException e4) {
            e4.printStackTrace();
            return "";
        } catch (BadPaddingException e5) {
            e5.printStackTrace();
            return "";
        } catch (UnsupportedEncodingException e6) {
            e6.printStackTrace();
            return "";
        }
    }

    public static String DESDecrypt(String cipherText) {
        try {
            if (TextUtils.isEmpty(cipherText)) {
                return "";
            }
            byte[] cipherText2 = Base64.decode(cipherText.getBytes("UTF-8"), 2);
            SecretKeySpec skeySpec = new SecretKeySpec(aes_key, "DESede");
            Cipher cipher = Cipher.getInstance("DESede");
            cipher.init(2, skeySpec);
            return new String(cipher.doFinal(cipherText2), "UTF-8");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return "";
        } catch (NoSuchPaddingException e2) {
            e2.printStackTrace();
            return "";
        } catch (InvalidKeyException e3) {
            e3.printStackTrace();
            return "";
        } catch (UnsupportedEncodingException e4) {
            e4.printStackTrace();
            return "";
        } catch (IllegalBlockSizeException e5) {
            e5.printStackTrace();
            return "";
        } catch (BadPaddingException e6) {
            e6.printStackTrace();
            return "";
        }
    }
}