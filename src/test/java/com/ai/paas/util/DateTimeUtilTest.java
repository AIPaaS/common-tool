package com.ai.paas.util;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.ai.paas.util.DateTimeUtil;

public class DateTimeUtilTest {

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
	public void testGetSeasonTimeInterval() {
		assertTrue("20180101-20180331".equals(DateTimeUtil.getSeasonTimeInterval(2018, 1)));
	}

	@Test
	public void testGetCurrentSeasonTime() {
		assertTrue(1 == DateTimeUtil.getCurrentSeasonTime(3));
		assertTrue(4 == DateTimeUtil.getCurrentSeasonTime(11));
	}

}
