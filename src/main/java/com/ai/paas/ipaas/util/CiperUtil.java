package com.ai.paas.ipaas.util;

import java.io.UnsupportedEncodingException;
import java.security.Key;
import java.security.Security;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.IvParameterSpec;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ai.paas.ipaas.PaaSConstant;

public class CiperUtil {
	private static transient final Logger log = LoggerFactory.getLogger(CiperUtil.class);

	public static final String KEY_ALGORITHM = "DES";
	public static final String DES_ECB_ALGORITHM = "DES/ECB/PKCS5Padding";
	public static final String DES_CBC_ALGORITHM = "DES/CBC/PKCS5Padding";
	public static final String DES_CBC_NOPADDING = "DES/CBC/NoPadding";
	public static String SECURITY_KEY = "byjsy7!@#bjwqt7!";
	private static final byte[] KEY_IV = { 1, 2, 3, 4, 5, 6, 7, 8 };

	// 算法名称
	public static final String DES3_KEY_ALGORITHM = "desede";
	// 算法名称/加密模式/填充方式
	public static final String CIPHER_ALGORITHM = "desede/CBC/NoPadding";

	public static final byte[] DES_CBC_IV = { 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00 };

	public static void setSECURITY_KEY(String sECURITY_KEY) {
		SECURITY_KEY = sECURITY_KEY;
	}

	public static byte[] genSecretKey() {
		try {
			KeyGenerator keyGenerator = KeyGenerator.getInstance(KEY_ALGORITHM);
			keyGenerator.init(56);
			SecretKey secretKey = keyGenerator.generateKey();
			return AsciiUtil.hex2Ascii(secretKey.getEncoded());
		} catch (Exception e) {
			log.error("exception:", e);
		}
		return null;
	}

	private static Key toKey(byte[] key) {
		try {
			DESKeySpec des = new DESKeySpec(key);
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(KEY_ALGORITHM);
			SecretKey secretKey = keyFactory.generateSecret(des);
			return secretKey;
		} catch (Exception e) {
			log.error("exception:", e);
		}
		return null;
	}

	public static byte[] encrypt(byte[] data, byte[] key, String algorithm) {
		try {
			Key k = toKey(key);
			Cipher cipher = Cipher.getInstance(algorithm);
			if (DES_CBC_ALGORITHM.equals(algorithm) || DES_CBC_NOPADDING.equals(algorithm)) {
				IvParameterSpec spec = new IvParameterSpec(DES_CBC_IV);
				cipher.init(Cipher.ENCRYPT_MODE, k, spec);
			} else {
				cipher.init(Cipher.ENCRYPT_MODE, k);
			}
			return cipher.doFinal(data);
		} catch (Exception e) {
			log.error("exception:", e);
		}
		return null;
	}

	public static byte[] decrypt(byte[] data, byte[] key, String algorithm) {
		try {
			Key k = toKey(key);
			Cipher cipher = Cipher.getInstance(algorithm);
			if (DES_CBC_ALGORITHM.equals(algorithm) || DES_CBC_NOPADDING.equals(algorithm)) {
				IvParameterSpec spec = new IvParameterSpec(DES_CBC_IV);
				cipher.init(Cipher.DECRYPT_MODE, k, spec);
			} else {
				cipher.init(Cipher.DECRYPT_MODE, k);
			}
			return cipher.doFinal(data);
		} catch (Exception e) {
			log.error("exception:", e);
		}
		return null;
	}

	public static String encrypt(String securityKey, String data) {
		byte[] aa;
		try {
			aa = encrypt(data.getBytes(PaaSConstant.CHARSET_UTF8),
					AsciiUtil.ascii2Hex(securityKey.getBytes(PaaSConstant.CHARSET_UTF8)), DES_ECB_ALGORITHM);
			return new String(AsciiUtil.hex2Ascii(aa));
		} catch (UnsupportedEncodingException e) {
			log.error("exception:", e);
		}
		return null;
	}

	public static String decrypt(String securityKey, String data) {
		byte[] aa;
		try {
			aa = AsciiUtil.ascii2Hex(data.getBytes(PaaSConstant.CHARSET_UTF8));
			return new String(decrypt(aa, AsciiUtil.ascii2Hex(securityKey.getBytes(PaaSConstant.CHARSET_UTF8)),
					DES_ECB_ALGORITHM));
		} catch (UnsupportedEncodingException e) {
			log.error("exception:", e);
		}
		return null;
	}

	public static String encrypt(String securityKey, String data, String algorithm) {
		byte[] aa;
		try {
			aa = encrypt(data.getBytes(PaaSConstant.CHARSET_UTF8),
					AsciiUtil.ascii2Hex(securityKey.getBytes(PaaSConstant.CHARSET_UTF8)), algorithm);
			return new String(AsciiUtil.hex2Ascii(aa));
		} catch (UnsupportedEncodingException e) {
			log.error("exception:", e);
		}
		return null;
	}

	public static String decrypt(String securityKey, String data, String algorithm) {
		byte[] aa;
		try {
			aa = AsciiUtil.ascii2Hex(data.getBytes(PaaSConstant.CHARSET_UTF8));
			return new String(
					decrypt(aa, AsciiUtil.ascii2Hex(securityKey.getBytes(PaaSConstant.CHARSET_UTF8)), algorithm));
		} catch (UnsupportedEncodingException e) {
			log.error("exception:", e);
		}
		return null;
	}

	public static String des3Encrypt(String key, String data) throws Exception {
		return new String(
				AsciiUtil.hex2Ascii(des3EncodeCBC(key.getBytes(PaaSConstant.CHARSET_UTF8), KEY_IV, data.getBytes())));
	}

	public static String des3Decrypt(String key, String data) throws Exception {
		byte[] aa = AsciiUtil.ascii2Hex(data.getBytes(PaaSConstant.CHARSET_UTF8));
		return new String(des3DecodeCBC(key.getBytes(PaaSConstant.CHARSET_UTF8), KEY_IV, aa),
				PaaSConstant.CHARSET_UTF8);
	}

	/**
	 * CBC加密
	 * 
	 * @param key
	 *            密钥
	 * @param keyiv
	 *            IV
	 * @param data
	 *            明文
	 * @return Base64编码的密文
	 * @throws Exception
	 */
	public static byte[] des3EncodeCBC(byte[] key, byte[] keyiv, byte[] data) throws Exception {
		Security.addProvider(new BouncyCastleProvider());
		Key deskey = keyGenerator(new String(key));
		Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
		IvParameterSpec ips = new IvParameterSpec(keyiv);
		cipher.init(Cipher.ENCRYPT_MODE, deskey, ips);
		byte[] bOut = cipher.doFinal(data);
		return bOut;
	}

	/**
	 * 
	 * 生成密钥key对象
	 * 
	 * @param KeyStr
	 *            密钥字符串
	 * @return 密钥对象
	 * @throws InvalidKeyException
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
	 * @throws Exception
	 */
	private static Key keyGenerator(String keyStr) throws Exception {
		byte input[] = HexString2Bytes(keyStr);
		DESedeKeySpec KeySpec = new DESedeKeySpec(input);
		SecretKeyFactory KeyFactory = SecretKeyFactory.getInstance(DES3_KEY_ALGORITHM);
		return ((Key) (KeyFactory.generateSecret(((java.security.spec.KeySpec) (KeySpec)))));
	}

	private static int parse(char c) {
		if (c >= 'a')
			return (c - 'a' + 10) & 0x0f;
		if (c >= 'A')
			return (c - 'A' + 10) & 0x0f;
		return (c - '0') & 0x0f;
	}

	// 从十六进制字符串到字节数组转换
	private static byte[] HexString2Bytes(String hexstr) {
		byte[] b = new byte[hexstr.length() / 2];
		int j = 0;
		for (int i = 0; i < b.length; i++) {
			char c0 = hexstr.charAt(j++);
			char c1 = hexstr.charAt(j++);
			b[i] = (byte) ((parse(c0) << 4) | parse(c1));
		}
		return b;
	}

	/**
	 * CBC解密
	 * 
	 * @param key
	 *            密钥
	 * @param keyiv
	 *            IV
	 * @param data
	 *            Base64编码的密文
	 * @return 明文
	 * @throws Exception
	 */
	public static byte[] des3DecodeCBC(byte[] key, byte[] keyiv, byte[] data) throws Exception {
		Key deskey = keyGenerator(new String(key));
		Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
		IvParameterSpec ips = new IvParameterSpec(keyiv);
		cipher.init(Cipher.DECRYPT_MODE, deskey, ips);
		byte[] bOut = cipher.doFinal(data);
		return bOut;
	}

	public static void main(String[] args) throws Exception {

		String bb = CiperUtil.decrypt("BaryTukyTukyBary", "7e340b5299f34582");
		System.out.println("bb:" + bb);
		System.out.println(CiperUtil.encrypt(SECURITY_KEY, "admin"));

		String key = "6C4E60E55552386C759569836DC0F83869836DC0F838C0F7";
		String data = des3Encrypt(key, "amigoxie");
		byte[] bytes = des3EncodeCBC(key.getBytes("UTF-8"), KEY_IV, "amigoxie".getBytes("UTF-8"));
		System.out.println(data);
		System.out.println(des3Decrypt(key, data));
		System.out.println(new String(des3DecodeCBC(key.getBytes("UTF-8"), KEY_IV, bytes)));
	}
}
