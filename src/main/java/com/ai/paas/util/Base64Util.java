package com.ai.paas.util;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Base64.Encoder;

import com.ai.paas.Constant;

/**
 * Base64工具类
 * 
 * @author douxiaofeng
 *
 */
public class Base64Util {
	private static Encoder encoder = Base64.getMimeEncoder();
	private static Decoder decoder = Base64.getMimeDecoder();

	private Base64Util() {

	}

	/**
	 * 将字节进行编码
	 * 
	 * @param data
	 * @return
	 */
	public static String encode(String data) {
		if (StringUtil.isBlank(data))
			return null;
		return encoder.encodeToString(data.getBytes(StandardCharsets.UTF_8));
	}

	/**
	 * 将字节进行编码
	 * 
	 * @param data
	 * @return
	 */
	public static String encode(byte[] data) {
		return encoder.encodeToString(data);
	}

	/**
	 * 将base64编码的字符串还原为字节数组
	 * 
	 * @param data
	 * @return
	 */
	public static byte[] decode(String data) {
		return decoder.decode(data);
	}

	/**
	 * 将base64编码字符串还原为真正字符串
	 * 
	 * @param data
	 * @return
	 */
	public static String decodeAsString(String data) {
		if (StringUtil.isBlank(data))
			return null;
		return new String(decoder.decode(data), StandardCharsets.UTF_8);
	}

	public static void main(String[] args) throws Exception {
		String test = "this is a 中国 ,pls get out";
		System.out.println(
				new String(decode(encode(test.getBytes(Constant.CHARSET_UTF8))), Constant.CHARSET_UTF8));
		System.out.println(new String(decode(encode("http://www.test.com".getBytes(Constant.CHARSET_UTF8))),
				Constant.CHARSET_UTF8));
	}
}
