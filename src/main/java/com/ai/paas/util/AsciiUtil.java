package com.ai.paas.util;

import com.ai.paas.GeneralRuntimeException;

/**
 * 十六进制与字符转换工具类
 * 
 * @author douxiaofeng
 *
 */

public class AsciiUtil {
	private AsciiUtil() {

	}

	public static String asciiToHex(String asciiValue) {
		if (StringUtil.isBlank(asciiValue))
			return null;
		char[] chars = asciiValue.toCharArray();
		StringBuilder hex = new StringBuilder();
		for (int i = 0; i < chars.length; i++) {
			hex.append(Integer.toHexString((int) chars[i]));
		}
		return hex.toString();
	}

	public static String hexToASCII(String hexValue) {
		if (StringUtil.isBlank(hexValue))
			return null;
		if (hexValue.length() % 2 != 0) {
			throw new GeneralRuntimeException("The hex value length must be even!");
		}
		StringBuilder output = new StringBuilder("");
		for (int i = 0; i < hexValue.length(); i += 2) {
			String str = hexValue.substring(i, i + 2);
			output.append((char) Integer.parseInt(str, 16));
		}
		return output.toString();
	}

	/**
	 * 十六进制字节转换为字符
	 * 
	 * @param in
	 * @return
	 */
	public static byte[] hex2Ascii(byte[] in) {
		byte[] temp1 = new byte[1];
		byte[] temp2 = new byte[1];
		byte[] out = new byte[in.length * 2];
		int i = 0;
		for (int j = 0; i < in.length; ++i) {
			temp1[0] = in[i];
			temp1[0] = (byte) (temp1[0] >>> 4);
			temp1[0] = (byte) (temp1[0] & 0xF);
			temp2[0] = in[i];
			temp2[0] = (byte) (temp2[0] & 0xF);
			if ((temp1[0] >= 0) && (temp1[0] <= 9))
				out[j] = (byte) (temp1[0] + 48);
			else if ((temp1[0] >= 10) && (temp1[0] <= 15)) {
				out[j] = (byte) (temp1[0] + 87);
			}

			if ((temp2[0] >= 0) && (temp2[0] <= 9))
				out[(j + 1)] = (byte) (temp2[0] + 48);
			else if ((temp2[0] >= 10) && (temp2[0] <= 15)) {
				out[(j + 1)] = (byte) (temp2[0] + 87);
			}
			j += 2;
		}
		return out;
	}

	/**
	 * 字符转换为十六进制
	 * 
	 * @param in
	 * @return
	 */
	public static byte[] ascii2Hex(byte[] in) {
		byte[] temp1 = new byte[1];
		byte[] temp2 = new byte[1];
		int i = 0;
		byte[] out = new byte[in.length / 2];
		for (int j = 0; i < in.length; ++j) {
			temp1[0] = in[i];
			temp2[0] = in[(i + 1)];
			if ((temp1[0] >= 48) && (temp1[0] <= 57)) {
				int tmp53_52 = 0;
				byte[] tmp53_51 = temp1;
				tmp53_51[tmp53_52] = (byte) (tmp53_51[tmp53_52] - 48);
				temp1[0] = (byte) (temp1[0] << 4);

				temp1[0] = (byte) (temp1[0] & 0xF0);
			} else if ((temp1[0] >= 97) && (temp1[0] <= 102)) {
				int tmp101_100 = 0;
				byte[] tmp101_99 = temp1;
				tmp101_99[tmp101_100] = (byte) (tmp101_99[tmp101_100] - 87);
				temp1[0] = (byte) (temp1[0] << 4);
				temp1[0] = (byte) (temp1[0] & 0xF0);
			}

			if ((temp2[0] >= 48) && (temp2[0] <= 57)) {
				int tmp149_148 = 0;
				byte[] tmp149_146 = temp2;
				tmp149_146[tmp149_148] = (byte) (tmp149_146[tmp149_148] - 48);

				temp2[0] = (byte) (temp2[0] & 0xF);
			} else if ((temp2[0] >= 97) && (temp2[0] <= 102)) {
				int tmp192_191 = 0;
				byte[] tmp192_189 = temp2;
				tmp192_189[tmp192_191] = (byte) (tmp192_189[tmp192_191] - 87);

				temp2[0] = (byte) (temp2[0] & 0xF);
			}
			out[j] = (byte) (temp1[0] | temp2[0]);
			i += 2;
		}
		return out;
	}

	public static void main(String[] args) {
		String ascii = "A simple Java program1";

		// Step-1 - Convert ASCII string to char array
		char[] ch = ascii.toCharArray();

		// Step-2 Iterate over char array and cast each element to Integer.
		StringBuilder builder = new StringBuilder();

		for (char c : ch) {
			int i = (int) c;
			// Step-3 Convert integer value to hex using toHexString() method.
			builder.append(Integer.toHexString(i).toUpperCase());
		}

		System.out.println("ASCII = " + ascii);
		System.out.println("Hex = " + builder.toString());

		System.out.println(new String(AsciiUtil.ascii2Hex(ascii.getBytes())));
	}
}
