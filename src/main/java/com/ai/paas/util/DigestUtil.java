package com.ai.paas.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class DigestUtil {

	private final static String DIGEST_SHA_256 = "SHA-256";

	private final static String DIGEST_SHA_512 = "SHA-512";

	private final static String DIGEST_MD5 = "MD5";

	private DigestUtil() {
	}

	/**
	 * 传入文本内容，返回 SHA-256 串
	 * 
	 * @param strText
	 * @return
	 */
	public static String SHA256(final String strText) {
		return digest(strText, DIGEST_SHA_256);
	}

	/**
	 * 传入文本内容，返回 SHA-512 串
	 * 
	 * @param strText
	 * @return
	 */
	public static String SHA512(final String strText) {
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
				byte byteBuffer[] = messageDigest.digest();

				return Base64Util.encode(byteBuffer).toLowerCase();
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			}
		}

		return strResult;
	}

	public static String MD5(String rawPass) {
		return digest(rawPass, DIGEST_MD5);
	}

	public static void main(String[] args) {
		String result = DigestUtil.MD5("111111");
		System.out.println("password=" + result);
		System.out.println(DigestUtil.SHA256("123456"));
		System.out.println(DigestUtil.SHA512("123456"));
	}

}
