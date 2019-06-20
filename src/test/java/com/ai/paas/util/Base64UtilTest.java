package com.ai.paas.util;

import static org.junit.Assert.*;


import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.ai.paas.Constant;
import com.ai.paas.util.Base64Util;

public class Base64UtilTest {

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
	public void testEncodeString() {
		String tt = "http://www.test.com/test?a=1&b=测试&c=this is a test";
		assertTrue(tt.equals(Base64Util.decodeAsString(Base64Util.encode(tt))));
	}

	@Test
	public void testEncodeByteArray() throws Exception {
		String tt = "http://www.test.com/test?a=1&b=测试&c=this is a test";
		assertTrue(tt.equals(Base64Util.decodeAsString(Base64Util.encode(tt.getBytes(Constant.CHARSET_UTF8)))));
	}

	@Test
	public void testDecode() throws Exception {
		String tt = "http://www.test.com/test?a=1&b=测试&c=this is a test";
		assertTrue(tt.equals(new String(Base64Util.decode(Base64Util.encode(tt)), Constant.CHARSET_UTF8)));
	}

	@Test
	public void testDecodeAsString() {
		String tt = "http://www.test.com/test?a=1&b=测试&c=this is a test";
		assertTrue(tt.equals(Base64Util.decodeAsString(Base64Util.encode(tt))));
	}

}
