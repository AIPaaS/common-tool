package test.com.ai.paas;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import test.com.ai.paas.util.AsciiUtilTest;
import test.com.ai.paas.util.AssertTest;
import test.com.ai.paas.util.Base64UtilTest;
import test.com.ai.paas.util.BeanUtilTest;
import test.com.ai.paas.util.CiperUtilTest;
import test.com.ai.paas.util.HttpUtilTest;

@RunWith(Suite.class)
@SuiteClasses({ AsciiUtilTest.class, AssertTest.class, HttpUtilTest.class, Base64UtilTest.class, BeanUtilTest.class,
		CiperUtilTest.class })
public class AllTests {

}
