package test.com.ai.paas.util;

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


	@Test
	public void testGetDateTime() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetMonth() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetYear() {
		fail("Not yet implemented");
	}

	@Test
	public void testDateDiffDateDate() {
		fail("Not yet implemented");
	}

	@Test
	public void testDateDiffStringStringString() {
		fail("Not yet implemented");
	}

	@Test
	public void testIsLeapDate() {
		fail("Not yet implemented");
	}

	@Test
	public void testIsLeapStringString() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetWorkDay() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetWorkDayIntDate() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetWorkDayIntString() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetWorkDayIntStringString() {
		fail("Not yet implemented");
	}

	@Test
	public void testMonthAddIntStringString() {
		fail("Not yet implemented");
	}

	@Test
	public void testMonthAddIntString() {
		fail("Not yet implemented");
	}

	@Test
	public void testMonthAddIntDate() {
		fail("Not yet implemented");
	}

	@Test
	public void testDateAddIntStringString() {
		fail("Not yet implemented");
	}

	@Test
	public void testDateAddIntString() {
		fail("Not yet implemented");
	}

	@Test
	public void testDateAddIntDate() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetDefaultSysDate() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetNowTimeStamp() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetNowDate() {
		fail("Not yet implemented");
	}

	@Test
	public void testTimeStampToStringTimestampString() {
		fail("Not yet implemented");
	}

	@Test
	public void testTimeStampToStringTimestamp() {
		fail("Not yet implemented");
	}

	@Test
	public void testDateToStringDateString() {
		fail("Not yet implemented");
	}

	@Test
	public void testFormatTimeString() {
		fail("Not yet implemented");
	}

	@Test
	public void testDateToStringDate() {
		fail("Not yet implemented");
	}

	@Test
	public void testStringToTimestamp() {
		fail("Not yet implemented");
	}

	@Test
	public void testStringToDateString() {
		fail("Not yet implemented");
	}

	@Test
	public void testStringToDateStringString() {
		fail("Not yet implemented");
	}

	@Test
	public void testDateTrans() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetCurrYear() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetCurrMonth() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetCurrDay() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetCurMonthDayNumber() {
		fail("Not yet implemented");
	}

	@Test
	public void testLastMnthBegDateString() {
		fail("Not yet implemented");
	}

	@Test
	public void testLastMnthBegDateStringString() {
		fail("Not yet implemented");
	}

	@Test
	public void testMonthBegDate() {
		fail("Not yet implemented");
	}

	@Test
	public void testLastMnthEndDateString() {
		fail("Not yet implemented");
	}

	@Test
	public void testLastMnthEndDateStringString() {
		fail("Not yet implemented");
	}

	@Test
	public void testMonthEndDate() {
		fail("Not yet implemented");
	}

	@Test
	public void testFormatDate() {
		fail("Not yet implemented");
	}

	@Test
	public void testFormatDateTime() {
		fail("Not yet implemented");
	}

	@Test
	public void testFormatDateString() {
		fail("Not yet implemented");
	}

	@Test
	public void testParseString() {
		fail("Not yet implemented");
	}

	@Test
	public void testParseStringString() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetLastMonth() {
		fail("Not yet implemented");
	}

	@Test
	public void testFormatDuring() {
		fail("Not yet implemented");
	}

	@Test
	public void testFormatTimeDuringHour() {
		fail("Not yet implemented");
	}

	@Test
	public void testFomateTimeS2S() {
		fail("Not yet implemented");
	}

	@Test
	public void testMain() {
		fail("Not yet implemented");
	}

	@Test
	public void testCheckDate() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetPreviousDate7() {
		fail("Not yet implemented");
	}

	@Test
	public void testFormatDatemhs() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetSysDate() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetLastDay() {
		fail("Not yet implemented");
	}

	@Test
	public void testCompareDate() {
		fail("Not yet implemented");
	}

	@Test
	public void testDateAddMinutes() {
		fail("Not yet implemented");
	}

	@Test
	public void testHPTimeAddMinutes() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetSysNowTime() {
		fail("Not yet implemented");
	}

}
