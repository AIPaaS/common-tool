package com.ai.paas.util;

import java.sql.Timestamp;
import java.util.Date;

import net.sf.cglib.core.Converter;

public class PrimitiveConverter implements Converter {

    @SuppressWarnings("rawtypes")
    @Override
    public Object convert(Object value, Class target, Object context) {
        if (value instanceof Integer) {
            return value;
        }
        if (value instanceof Long) {
            return value;
        }
        if (value instanceof Date && Timestamp.class.isAssignableFrom(target)) {
            return new Timestamp(((Date) value).getTime());
        }
        if (value instanceof Timestamp && Date.class.isAssignableFrom(target)) {
            return new Date(((Timestamp) value).getTime());
        }
        if (value instanceof Double) {
            return value;
        }
        if (value instanceof Float) {
            return value;
        }
        if (value instanceof Boolean) {
            return value;
        }
        if (value instanceof Short) {
            return value;
        }

        if (value instanceof Character) {
            return value;
        }
        if (value instanceof Byte) {
            return value;
        }

        return value;
    }
}