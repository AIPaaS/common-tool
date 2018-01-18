package test.com.ai.paas.util;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.ai.paas.util.BeanUtil;

import test.com.ai.paas.util.domain.User;
import test.com.ai.paas.util.domain.UserWithGender;

public class BeanUtilTest {

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
	public void testCopyObject() throws Exception {
		User user = new User("123456", "test");
		UserWithGender userB = BeanUtil.copy(user, UserWithGender.class);
		assertTrue(userB.getUserName().equals(user.getUserName()));
	}

	@Test
	public void testCopyObjectObject() {
		User user = new User("123456", "test");
		UserWithGender userB = new UserWithGender();
		BeanUtil.copy(user, userB);
		assertTrue(userB.getUserName().equals(user.getUserName()));
		assertNull(userB.getGender());
	}

}
