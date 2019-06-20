package com.ai.paas;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.ai.paas.util.AsciiUtilTest;
import com.ai.paas.util.AssertTest;
import com.ai.paas.util.Base64UtilTest;
import com.ai.paas.util.BeanUtilTest;
import com.ai.paas.util.CiperUtilTest;
import com.ai.paas.util.HttpUtilTest;
import com.ai.paas.util.JsonUtilTest;

@RunWith(Suite.class)
@SuiteClasses({ AsciiUtilTest.class, AssertTest.class, HttpUtilTest.class, Base64UtilTest.class, BeanUtilTest.class,
		CiperUtilTest.class, JsonUtilTest.class })
public class AllTests {

}
