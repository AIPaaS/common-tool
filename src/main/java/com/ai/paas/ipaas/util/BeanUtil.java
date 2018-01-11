package com.ai.paas.ipaas.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
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

	@SuppressWarnings("unchecked")
	public static <T> T copy(Object t) throws Exception {
		ByteArrayOutputStream bos = null;
		ObjectInputStream ois = null;
		try {
			bos = new ByteArrayOutputStream();
			serializeToOutputStream(t, bos);
			byte[] bytes = bos.toByteArray();
			ois = new ObjectInputStream(new ByteArrayInputStream(bytes));
			T clone = (T) ois.readObject();

			return clone;
		} finally {
			if (null != bos)
				bos.close();
			if (null != ois)
				ois.close();
		}
	}

	private static void serializeToOutputStream(Object ser, OutputStream os) throws IOException {
		ObjectOutputStream oos = null;
		try {
			oos = new ObjectOutputStream(os);
			oos.writeObject(ser);
			oos.flush();
		} finally {
			oos.close();
		}
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
		System.out.println((String)BeanUtil.copy(dd));
		String des = "";
		BeanUtil.copy(dd, des);
		System.out.println(des+"====");

	}
}
