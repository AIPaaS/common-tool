package com.ai.paas.ipaas.serialize.impl.kryo;

import com.esotericsoftware.kryo.Kryo;

/**
 * CAUSION this is only for test purpose since both kryo and this class are not
 * thread-safe
 *
 * @author lishen
 */
public class SingletonKryoFactory extends KryoFactory {

	// private final Kryo instance = createKryo();
	private Kryo instance;

	@Override
	public Kryo getKryo() {
		if (instance == null) {
			instance = createKryo();
		}
		return instance;
	}
}
