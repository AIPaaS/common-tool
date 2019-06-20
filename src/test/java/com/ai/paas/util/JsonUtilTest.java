package com.ai.paas.util;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.ai.paas.serialize.TypeGetter;
import com.ai.paas.util.JsonUtil;
import com.ai.paas.util.domain.User;

public class JsonUtilTest {

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
	public void testToJson() {
		User user = new User("test", "huhu");
		String json = JsonUtil.toJson(user);
		User user1 = JsonUtil.fromJson(json, User.class);
		assertTrue(user.getUserId().equals(user1.getUserId()));
	}

	@Test
	public void testFromJsonStringClassOfT() {
		User user = new User("test", "huhu");
		String json = JsonUtil.toJson(user);
		User user1 = JsonUtil.fromJson(json, User.class);
		assertTrue(user.getUserId().equals(user1.getUserId()));
	}

	@Test
	public void testFromJsonStringTypeGetterOfT() {
		List<User> users = new ArrayList<>();
		User user = new User("test", "huhu");
		users.add(user);
		String json = JsonUtil.toJson(users);
		List<User> users1 = JsonUtil.fromJson(json, new TypeGetter<List<User>>() {
		});
		assertTrue(users1.get(0).getUserId().equals(user.getUserId()));
	}

}
