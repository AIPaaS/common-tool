package test.com.ai.paas.util;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.ai.paas.util.Assert;

public class AssertTest {

	@Rule
	public ExpectedException thrown = ExpectedException.none();

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
	public void testNotNullObject() {
		Object obj = null;
		thrown.expect(IllegalArgumentException.class);
		Assert.notNull(obj);

		obj = new Object();
		Assert.notNull(obj);
	}

	@Test
	public void testNotNullObjectString() {
		Object obj = null;
		thrown.expect(IllegalArgumentException.class);
		thrown.expectMessage("para is null!");
		Assert.notNull(obj, "para is null!");
		obj = new Object();
		Assert.notNull(obj);
	}

	@Test
	public void testNotNullList() {
		List<String> objs = null;
		thrown.expect(IllegalArgumentException.class);
		Assert.notNull(objs);

		objs = new ArrayList<>();
		Assert.notNull(objs);
		thrown.expect(IllegalArgumentException.class);
		objs.add("123456");
		Assert.notNull(objs);
	}

	@Test
	public void testNotNullListString() {
		List<String> objs = null;
		thrown.expect(IllegalArgumentException.class);
		thrown.expectMessage("arg is null!");
		Assert.notNull(objs, "arg is null!");

		objs = new ArrayList<>();
		Assert.notNull(objs);
		thrown.expect(IllegalArgumentException.class);
		thrown.expectMessage("arg is null!");
		objs.add("123456");
		Assert.notNull(objs);
	}

	@Test
	public void testNotNullMap() {
		Map<String, String> objs = null;
		thrown.expect(IllegalArgumentException.class);
		Assert.notNull(objs);

		objs = new HashMap<>();
		Assert.notNull(objs);
		thrown.expect(IllegalArgumentException.class);
		objs.put("123456", "kkkk");
		Assert.notNull(objs);
	}

	@Test
	public void testNotNullMapString() {
		Map<String, String> objs = null;
		thrown.expect(IllegalArgumentException.class);
		thrown.expectMessage("arg is null!");
		Assert.notNull(objs, "arg is null!");

		objs = new HashMap<>();
		Assert.notNull(objs);
		thrown.expect(IllegalArgumentException.class);
		thrown.expectMessage("arg is null!");
		objs.put("123456", "llll");
		Assert.notNull(objs);
	}

	@Test
	public void testNotNullObjectArray() {
		Object[] objs = null;
		thrown.expect(IllegalArgumentException.class);
		Assert.notNull(objs);

		objs = new Object[1];
		Assert.notNull(objs);
		thrown.expect(IllegalArgumentException.class);
		objs[0] = new Object();
		Assert.notNull(objs);
	}

	@Test
	public void testNotNullObjectArrayString() {
		Object[] objs = null;
		thrown.expect(IllegalArgumentException.class);
		thrown.expectMessage("arg is null!");
		Assert.notNull(objs, "arg is null!");

		objs = new Object[1];
		Assert.notNull(objs);
		thrown.expect(IllegalArgumentException.class);
		thrown.expectMessage("arg is null!");
		objs[0] = new Object();
		Assert.notNull(objs);
	}

}
