package com.lottery.common.util;

import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import static cn.bqmart.common.util.HttpUtils.logger;

public class Md5Utils {

    /**
     * MD5签名
     *
     * @param str
     * @return
     */
     static String encryptMD5(String str, String charset) {
        StringBuilder sb = new StringBuilder();
        for (byte b : md5(str, charset)) {
            sb.append(String.format("%02X", b));
        }
        return sb.toString().toLowerCase();
    }

    static String md5(String plainText) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(plainText.getBytes(Charset.defaultCharset()));
            byte b[] = md.digest();

            int i;

            StringBuffer buf = new StringBuffer("");
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0) {
                    i += 256;
                }
                if (i < 16) {
                    buf.append("0");
                }
                buf.append(Integer.toHexString(i));
            }

            // System.out.println("result: " + buf.toString().substring(8, 24));// 16位的加密
            return buf.toString();
        } catch (NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return "";
    }

    private static byte[] md5(String str, String charset) {
        if (charset == null) {
            charset = "UTF-8";
        }
        MessageDigest md5;
        try {
            md5 = MessageDigest.getInstance("MD5");
            return md5.digest(str.getBytes(charset));
        } catch (Exception e) {
            logger.error("MD5加密出错。数据是：" + str, e);
        }
        return null;
    }
}
