package com.ai.paas.util;

import java.util.HashMap;
import java.util.Map;

import net.sf.cglib.beans.BeanCopier;

public class BeanUtil {

	private static Map<String, BeanCopier> beanCopierMap = new HashMap<String, BeanCopier>();

	private BeanUtil() {

	}

	public static <T> T copy(Object from, Class<T> clazz) throws Exception {
		String json = JsonUtil.toJson(from);
		return JsonUtil.fromJson(json, clazz);
	}

	/**
	 * @Title: copy
	 * @param source
	 *            资源类
	 * @param target
	 *            目标类
	 */
	public static void copy(Object source, Object target) {
		String beanKey = generateKey(source.getClass(), target.getClass());
		BeanCopier copier = null;
		if (!beanCopierMap.containsKey(beanKey)) {
			copier = BeanCopier.create(source.getClass(), target.getClass(), false);
			beanCopierMap.put(beanKey, copier);
		} else {
			copier = beanCopierMap.get(beanKey);
		}
		copier.copy(source, target, null);
	}

	private static String generateKey(Class<?> class1, Class<?> class2) {
		return class1.toString() + class2.toString();
	}

	public static void main(String[] args) throws Exception {
		String dd = "SSSS";
		String clone = BeanUtil.copy(dd, String.class);
		System.out.println(clone);
		String des = "";
		BeanUtil.copy(dd, des);
		System.out.println(des + "====");

	}
}
