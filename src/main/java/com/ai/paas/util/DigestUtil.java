package com.ai.paas.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DigestUtil {
    private static final Logger log = LoggerFactory.getLogger(DigestUtil.class);

    private static final String DIGEST_SHA_256 = "SHA-256";

    private static final String DIGEST_SHA_512 = "SHA-512";

    private static final String DIGEST_MD5 = "MD5";

    private DigestUtil() {
    }

    /**
     * 传入文本内容，返回 SHA-256 串
     * 
     * @param strText
     * @return
     */
    public static String sha256(final String strText) {
        return digest(strText, DIGEST_SHA_256);
    }

    /**
     * 传入文本内容，返回 SHA-512 串
     * 
     * @param strText
     * @return
     */
    public static String sha512(final String strText) {
        return digest(strText, DIGEST_SHA_512);
    }

    /**
     * 字符串 SHA 加密
     * 
     * @param strSourceText
     * @return
     */
    private static String digest(final String strText, final String strType) {
        // 返回值
        String strResult = null;
        // 是否是有效字符串
        if (strText != null && strText.length() > 0) {
            try {
                // SHA 加密开始
                // 创建加密对象 并傳入加密類型
                MessageDigest messageDigest = MessageDigest.getInstance(strType);
                // 传入要加密的字符串
                messageDigest.update(strText.getBytes());
                // 得到 byte 類型结果
                byte[] byteBuffer = messageDigest.digest();

                return Base64Util.encode(byteBuffer).toLowerCase();
            } catch (NoSuchAlgorithmException e) {
                log.error("can not find method,", e);
            }
        }

        return strResult;
    }

    public static String md5(String rawPass) {
        return digest(rawPass, DIGEST_MD5);
    }

    public static void main(String[] args) {
        String result = DigestUtil.md5("111111");
        log.info("result={}", result);
        log.info(DigestUtil.sha256("123456"));
        log.info(DigestUtil.sha512("123456"));
    }

}
