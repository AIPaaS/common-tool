package com.ai.paas.ipaas.serialize.impl.kryo;


public abstract class ReflectionUtils {

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static boolean checkZeroArgConstructor(Class clazz) {
		try {
			clazz.getDeclaredConstructor();
			return true;
		} catch (NoSuchMethodException e) {
			return false;
		}
	}
}
