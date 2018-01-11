package com.ai.paas.serialize.impl.kryo;

import com.esotericsoftware.kryo.Kryo;

public class PrototypeKryoFactory extends KryoFactory {

	public Kryo getKryo() {
		return createKryo();
	}
}
