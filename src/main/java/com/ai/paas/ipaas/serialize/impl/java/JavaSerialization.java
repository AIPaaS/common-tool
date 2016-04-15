package com.ai.paas.ipaas.serialize.impl.java;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


import com.ai.paas.ipaas.serialize.ObjectInput;
import com.ai.paas.ipaas.serialize.ObjectOutput;
import com.ai.paas.ipaas.serialize.Serialization;


public class JavaSerialization implements Serialization {

	public byte getContentTypeId() {
		return 3;
	}

	public String getContentType() {
		return "x-application/java";
	}

	public ObjectOutput serialize(OutputStream out) throws IOException {
		return new JavaObjectOutput(out);
	}

	public ObjectInput deserialize(InputStream is) throws IOException {
		return new JavaObjectInput(is);
	}

}