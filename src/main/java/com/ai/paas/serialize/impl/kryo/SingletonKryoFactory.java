package com.ai.paas.serialize.impl.kryo;

import com.esotericsoftware.kryo.Kryo;

/**
 * CAUSION this is only for test purpose since both kryo and this class are not
 * thread-safe
 *
 * @author lishen
 */
public class SingletonKryoFactory extends KryoFactory {

	private Kryo instance;

	@Override
	public Kryo getKryo() {
		if (instance == null) {
			instance = createKryo();
		}
		return instance;
	}
}
