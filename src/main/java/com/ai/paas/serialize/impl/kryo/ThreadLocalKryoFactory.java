package com.ai.paas.serialize.impl.kryo;

import com.esotericsoftware.kryo.Kryo;


public class ThreadLocalKryoFactory extends KryoFactory {

	private final ThreadLocal<Kryo> holder = ThreadLocal.withInitial(() -> {
			return createKryo();
	});

	public Kryo getKryo() {
		return holder.get();
	}
}
