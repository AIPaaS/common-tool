package com.ai.paas.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ai.paas.serialize.ObjectOutput;
import com.ai.paas.serialize.Serialization;
import com.ai.paas.serialize.impl.java.CompactedJavaSerialization;
import com.ai.paas.serialize.impl.java.JavaSerialization;
import com.ai.paas.serialize.impl.kryo.KryoFactory;
import com.ai.paas.serialize.impl.kryo.KryoSerialization;
import com.ai.paas.serialize.impl.nativejava.NativeJavaSerialization;

/**
 * 先根据spring定义的实现来序列化和反序列化 避免放开方法后谁不小心更改了系列化的机制
 * 
 * @author DOUXF
 *
 */
public class SerializeUtil {
    private static final Logger log = LoggerFactory.getLogger(SerializeUtil.class);

    private SerializeUtil() {
        //
    }

    private static Serialization javaSer = new JavaSerialization();
    private static Serialization compactedjavaSer = new CompactedJavaSerialization();
    private static Serialization nativejavaSer = new NativeJavaSerialization();
    private static Serialization kryoSer = new KryoSerialization();
    private static Serialization serialization = null;

    private static Serialization getInstance() {
        // 配置谁身上
        if (null == serialization) {
            // 获取系统属性
            String type = System.getProperty("serialization", "kryo");
            switch (type) {
            case "kryo":
                serialization = kryoSer;
                break;
            case "java":
                serialization = javaSer;
                break;
            case "compactedjava":
                serialization = compactedjavaSer;
                break;
            case "nativejava":
                serialization = nativejavaSer;
                break;
            default:
                // kryo
                serialization = kryoSer;
            }
        }
        return serialization;
    }

    @SuppressWarnings("rawtypes")
    public static void register(Class clazz) {
        if (getInstance() instanceof KryoSerialization) {
            KryoFactory.getDefaultFactory().registerClass(clazz);
        }
    }

    public static byte[] serialize(Object object) {
        if (object == null)
            return new byte[] {};
        if (log.isDebugEnabled()) {
            log.debug(" {}:{} transfer into bytes!", object.getClass(), object);
        }
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            ObjectOutput objectOutput = getInstance().serialize(baos);
            objectOutput.writeObject(object);
            objectOutput.flushBuffer();
            return baos.toByteArray();
        } catch (Exception e) {
            log.error("error to serialize object:{}", object, e);
        }
        return new byte[] {};
    }

    public static Object deserialize(byte[] bytes) {
        if (bytes == null)
            return null;
        try (ByteArrayInputStream bais = new ByteArrayInputStream(bytes);) {

            Object obj = getInstance().deserialize(bais).readObject();
            if (log.isDebugEnabled()) {
                log.debug("Bytes transfer into :{},{}", obj.getClass(), obj);
            }
            return obj;
        } catch (Exception e) {
            log.error("error to deserialize object:", e);
        }
        return null;
    }

    public static void main(String[] args) {
        SerializeUtil.register(String.class);
    }
}