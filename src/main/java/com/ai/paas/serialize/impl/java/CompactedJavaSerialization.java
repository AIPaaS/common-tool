package com.ai.paas.serialize.impl.java;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import com.ai.paas.serialize.ObjectInput;
import com.ai.paas.serialize.ObjectOutput;
import com.ai.paas.serialize.Serialization;


public class CompactedJavaSerialization implements Serialization {

	public byte getContentTypeId() {
		return 4;
	}

	public String getContentType() {
		return "x-application/compactedjava";
	}

	public ObjectOutput serialize(OutputStream out) throws IOException {
		return (ObjectOutput) new JavaObjectOutput(out, true);
	}

	public ObjectInput deserialize(InputStream is) throws IOException {
		return (ObjectInput) new JavaObjectInput(is, true);
	}

}