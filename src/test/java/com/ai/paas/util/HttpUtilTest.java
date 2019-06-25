package com.ai.paas.util;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.ai.paas.GeneralRuntimeException;
import com.ai.paas.util.HttpUtil;

public class HttpUtilTest {

    private String plainAddr = "http://www.sohu.com/";
    private String sslAddr = "http://www.baidu.com/";

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
    public void testDoGet() {
        String result = HttpUtil.doGet(plainAddr, null);
        System.out.println(result);
        assertTrue(result != null);
    }

    @Test
    public void testDoGetWithParam() {
        Map<String, String> param = new HashMap<>();
        param.put("test", "1");
        String result = HttpUtil.doGet(plainAddr, param);
        System.out.println(result);
        assertTrue(result != null);
    }

    @Test
    public void testDoPostWithParam() {
        Map<String, String> param = new HashMap<>();
        param.put("q", "测试");
        String result = HttpUtil.doPost("http://search.kdnet.net", param);
        System.out.println(result);
        assertTrue(result != null);
    }

    @Test
    public void testDoSSLGet() {
        String result = HttpUtil.doGetSSL(sslAddr, null);
        System.out.println(result);
        assertTrue(result != null);
    }

    @Test(expected = GeneralRuntimeException.class)
    public void testDoSSLPost() {
        // 找不到测试地址
        Map<String, Object> param = new HashMap<>();
        param.put("q", "spring");
        String result = HttpUtil.doPostSSL("https://stackoverflow.com/search", param);
        // 会报302异常
        assertTrue(result != null);
    }
}
