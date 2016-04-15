package com.ai.paas.ipaas.serialize.impl;

import java.util.LinkedHashSet;
import java.util.Set;

public abstract class SerializableClassRegistry {

    @SuppressWarnings("rawtypes")
	private static final Set<Class> registrations = new LinkedHashSet<Class>();

    /**
     * only supposed to be called at startup time
     */
    @SuppressWarnings("rawtypes")
	public static void registerClass(Class clazz) {
        registrations.add(clazz);
    }

    @SuppressWarnings("rawtypes")
	public static Set<Class> getRegisteredClasses() {
        return registrations;
    }
}
