package test.com.ai.paas.util;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.ai.paas.util.AsciiUtil;

public class AsciiUtilTest {

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
	public void testHex2Ascii() throws Exception {
		String s = "This is a test without Chinese! 1234567890";
		String o = AsciiUtil.hexToASCII(AsciiUtil.asciiToHex(s));
		assertTrue(s.equals(o));
	}
}
