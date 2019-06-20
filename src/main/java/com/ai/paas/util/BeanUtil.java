package com.ai.paas.util;

import net.sf.cglib.beans.BeanCopier;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * bean复制工具类
 * 
 * @author douxiaofeng
 *
 */
public final class BeanUtil {

    private static Map<String, BeanCopier> beanCopiers = new ConcurrentHashMap<>();

    private BeanUtil() {
        // 禁止私有化
    }

    private static String generateKey(Class<?> class1, Class<?> class2) {
        return class1.toString() + class2.toString();
    }

    /**
     * 使用CGLIB进行复制，速度最快，但不能进行深度复制
     * 
     * @param source
     * @param target
     */
    public static void copy(Object source, Object target) {
        String beanKey = generateKey(source.getClass(), target.getClass());
        BeanCopier copier = null;
        if (!beanCopiers.containsKey(beanKey)) {
            copier = BeanCopier.create(source.getClass(), target.getClass(), true);
            beanCopiers.put(beanKey, copier);
        } else {
            copier = beanCopiers.get(beanKey);
        }
        copier.copy(source, target, new PrimitiveConverter());
    }

    /**
     * 对象是否存在指定属性
     * 
     * @param object
     * @param fieldName
     * @return
     */
    public static boolean hasProperty(Object object, String fieldName) {
        if (null == object || StringUtil.isBlank(fieldName))
            return false;
        Field[] fields = object.getClass().getDeclaredFields();
        if (fields == null || fields.length == 0) {
            return false;
        }
        for (Field field : fields) {
            String fieldN = field.getName();
            if (fieldN.equals(fieldName)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 深度复制，包括里面的Map、List
     * 
     * @param destSVO
     * @param orignSVO
     * @throws SystemException
     */
    public static void deepCopy(Object source, Object target, String... ignoreProperties) {
        /* 1.源对象与目标对象都不能为空 */
        Assert.notNull(source);
        Assert.notNull(target);
        /* 2.深度拷贝 */
        List<String> ignProperties = new ArrayList<>();
        ignProperties.addAll(Arrays.asList(ignoreProperties));
        BeanUtils.copyProperties(source, target, ignProperties.toArray(new String[ignProperties.size()]));
    }

    /**
     * 深度复制，包括里面的Map、List 忽略null的字段
     * 
     * @param source
     * @param target
     * @param ignoreProperties
     */
    public static void deepCopyIgnoreNull(Object source, Object target, String... ignoreProperties) {
        Assert.notNull(source);
        Assert.notNull(target);
        List<String> ignProperties = new ArrayList<>();
        ignProperties.addAll(Arrays.asList(ignoreProperties));
        ignProperties.addAll(Arrays.asList(getNullPropertyNames(source)));
        BeanUtils.copyProperties(source, target, ignProperties.toArray(new String[ignProperties.size()]));
    }

    /**
     * 判断对象是否有某方法
     * 
     * @param obj
     * @param methodName
     * @return
     */
    public static boolean hasMethod(Object obj, String methodName) {
        if (!StringUtil.isBlank(methodName)) {
            Method[] methods = obj.getClass().getDeclaredMethods();
            for (Method method : methods) {
                if (methodName.equals(method.getName())) {
                    return true;
                }
            }
        }
        return false;
    }

    public static String[] getNullPropertyNames(Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();

        Set<String> emptyNames = new HashSet<>();
        for (java.beans.PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null)
                emptyNames.add(pd.getName());
        }
        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }

    public static void main(String[] args) {
    }
}
