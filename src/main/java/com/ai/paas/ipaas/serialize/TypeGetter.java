package com.ai.paas.ipaas.serialize;

import com.fasterxml.jackson.core.type.TypeReference;

/**
 * 用于获取泛型类的具体类型
 * 
 * @author douxiaofeng
 *
 * @param <T>
 */
public abstract class TypeGetter<T> extends TypeReference<T> {

}
