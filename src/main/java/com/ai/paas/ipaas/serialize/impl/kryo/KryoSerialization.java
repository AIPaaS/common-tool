package com.ai.paas.ipaas.serialize.impl.kryo;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


import com.ai.paas.ipaas.serialize.ObjectInput;
import com.ai.paas.ipaas.serialize.ObjectOutput;
import com.ai.paas.ipaas.serialize.OptimizedSerialization;


public class KryoSerialization implements OptimizedSerialization {

	public byte getContentTypeId() {
		return 8;
	}

	public String getContentType() {
		return "x-application/kryo";
	}

	public ObjectOutput serialize(OutputStream out) throws IOException {
		return new KryoObjectOutput(out);
	}

	public ObjectInput deserialize(InputStream is) throws IOException {
		return new KryoObjectInput(is);
	}
}