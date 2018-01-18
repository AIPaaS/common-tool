package test.com.ai.paas.util;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.ai.paas.util.CiperUtil;

public class CiperUtilTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testEncryptByteArrayByteArrayString() {
		String s = "this is a test!";
		String key = "1234567890123456";
		byte[] ss = CiperUtil.encrypt(s.getBytes(), key.getBytes(), CiperUtil.DES_ECB_ALGORITHM);
		byte[] ori = CiperUtil.decrypt(ss, key.getBytes(), CiperUtil.DES_ECB_ALGORITHM);
		assertTrue(s.equals(new String(ori)));
		ss = CiperUtil.encrypt(s.getBytes(), key.getBytes(), CiperUtil.DES_CBC_ALGORITHM);
		ori = CiperUtil.decrypt(ss, key.getBytes(), CiperUtil.DES_CBC_ALGORITHM);
		assertTrue(s.equals(new String(ori)));
		// 必须8位的倍数
		s = "thisisis";
		ss = CiperUtil.encrypt(s.getBytes(), key.getBytes(), CiperUtil.DES_CBC_NOPADDING);
		ori = CiperUtil.decrypt(ss, key.getBytes(), CiperUtil.DES_CBC_NOPADDING);
		assertTrue(s.equals(new String(ori)));
	}

	@Test
	public void testDecryptByteArrayByteArrayString() {
		String s = "this is a test!";
		String key = "1234567890123456";
		byte[] ss = CiperUtil.encrypt(s.getBytes(), key.getBytes(), CiperUtil.DES_ECB_ALGORITHM);
		byte[] ori = CiperUtil.decrypt(ss, key.getBytes(), CiperUtil.DES_ECB_ALGORITHM);
		assertTrue(s.equals(new String(ori)));
		ss = CiperUtil.encrypt(s.getBytes(), key.getBytes(), CiperUtil.DES_CBC_ALGORITHM);
		ori = CiperUtil.decrypt(ss, key.getBytes(), CiperUtil.DES_CBC_ALGORITHM);
		assertTrue(s.equals(new String(ori)));
		// 必须8位的倍数
		s = "thisisis";
		ss = CiperUtil.encrypt(s.getBytes(), key.getBytes(), CiperUtil.DES_CBC_NOPADDING);
		ori = CiperUtil.decrypt(ss, key.getBytes(), CiperUtil.DES_CBC_NOPADDING);
		assertTrue(s.equals(new String(ori)));
	}

	@Test
	public void testEncryptStringString() {
		String s = "this is a test!";
		String key = "1234567890123456";
		String a = CiperUtil.encrypt(key, s);
		assertTrue(s.equals(CiperUtil.decrypt(key, a)));
	}

	@Test
	public void testDecryptStringString() {
		String s = "this is a test!";
		String key = "1234567890123456";
		String a = CiperUtil.encrypt(key, s);
		assertTrue(s.equals(CiperUtil.decrypt(key, a)));
	}

	@Test
	public void testEncryptStringStringString() {
		String s = "this is a test!";
		String key = "1234567890123456";
		String a = CiperUtil.encrypt(key, s, CiperUtil.DES_CBC_ALGORITHM);
		assertTrue(s.equals(CiperUtil.decrypt(key, a, CiperUtil.DES_CBC_ALGORITHM)));
	}

	@Test
	public void testDecryptStringStringString() {
		String s = "this is a test!";
		String key = "1234567890123456";
		String a = CiperUtil.encrypt(key, s, CiperUtil.DES_CBC_ALGORITHM);
		assertTrue(s.equals(CiperUtil.decrypt(key, a, CiperUtil.DES_CBC_ALGORITHM)));
	}

	@Test
	public void testDes3Encrypt() throws Exception {
		String s = "1234567890123456";
		String key = "6C4E60E55552386C759569836DC0F83869836DC0F838C0F7";
		String a = CiperUtil.des3Encrypt(key, s);
		assertTrue(s.equals(CiperUtil.des3Decrypt(key, a)));
	}

	@Test
	public void testDes3Decrypt() throws Exception {
		String s = "1234567890123456";
		String key = "6C4E60E55552386C759569836DC0F83869836DC0F838C0F7";
		String a = CiperUtil.des3Encrypt(key, s);
		assertTrue(s.equals(CiperUtil.des3Decrypt(key, a)));
	}

	@Test
	public void testDes3EncodeCBC() throws Exception {
		String s = "1234567890123456";
		String key = "6C4E60E55552386C759569836DC0F83869836DC0F838C0F7";
		byte[] a = CiperUtil.des3EncodeCBC(key.getBytes(), CiperUtil.DES_CBC_IV, s.getBytes());
		assertTrue(s.equals(new String(CiperUtil.des3DecodeCBC(key.getBytes(), CiperUtil.DES_CBC_IV, a))));
	}

	@Test
	public void testDes3DecodeCBC() throws Exception {
		String s = "1234567890123456";
		String key = "6C4E60E55552386C759569836DC0F83869836DC0F838C0F7";
		byte[] a = CiperUtil.des3EncodeCBC(key.getBytes(), CiperUtil.DES_CBC_IV, s.getBytes());
		assertTrue(s.equals(new String(CiperUtil.des3DecodeCBC(key.getBytes(), CiperUtil.DES_CBC_IV, a))));
	}

}
