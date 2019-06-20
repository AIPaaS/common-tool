package com.ai.paas.util;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.ai.paas.util.Base64Util;
import com.ai.paas.util.DigestUtil;

public class DigestUtilTest {

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
	public void testSHA256() {
		String s = "this is a test";
		byte[] sha256 = org.apache.commons.codec.digest.DigestUtils.sha256(s);
		assertTrue(DigestUtil.sha256(s).equals(Base64Util.encode(sha256).toLowerCase()));
	}

	@Test
	public void testSHA512() {
		String s = "this is a test";
		byte[] sha512 = org.apache.commons.codec.digest.DigestUtils.sha512(s);
		assertTrue(DigestUtil.sha512(s).equals(Base64Util.encode(sha512).toLowerCase()));
	}

	@Test
	public void testMD5() {
		fail("Not yet implemented");
	}

}
