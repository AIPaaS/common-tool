package com.ai.paas.serialize.impl;

import java.util.Collection;

public interface SerializationOptimizer {

    @SuppressWarnings("rawtypes")
	Collection<Class> getSerializableClasses();
}
