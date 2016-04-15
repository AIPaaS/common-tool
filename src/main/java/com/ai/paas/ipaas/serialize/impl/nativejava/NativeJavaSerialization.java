package com.ai.paas.ipaas.serialize.impl.nativejava;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import com.ai.paas.ipaas.serialize.ObjectInput;
import com.ai.paas.ipaas.serialize.ObjectOutput;
import com.ai.paas.ipaas.serialize.Serialization;


public class NativeJavaSerialization implements Serialization {

	public static final String NAME = "nativejava";

	public byte getContentTypeId() {
		return 7;
	}

	public String getContentType() {
		return "x-application/nativejava";
	}

	public ObjectOutput serialize(OutputStream output) throws IOException {
		return new NativeJavaObjectOutput(output);
	}

	public ObjectInput deserialize(InputStream input) throws IOException {
		return new NativeJavaObjectInput(input);
	}
}
