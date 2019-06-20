package com.ai.paas.serialize.impl.kryo;

public  class ReflectionUtil {

    private ReflectionUtil() {
        
    }
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
