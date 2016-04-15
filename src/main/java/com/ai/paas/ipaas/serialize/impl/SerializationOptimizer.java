package com.ai.paas.ipaas.serialize.impl;

import java.util.Collection;

public interface SerializationOptimizer {

    @SuppressWarnings("rawtypes")
	Collection<Class> getSerializableClasses();
}
