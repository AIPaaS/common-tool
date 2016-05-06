package com.ai.paas.ipaas.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class CloneTool {
	private static Gson gson = (new GsonBuilder())
			.enableComplexMapKeySerialization().create();

	private CloneTool() {

	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static <T> T clone(Object o, Class clazz) {
		String json = gson.toJson(o);
		return (T) gson.fromJson(json, clazz);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static <T> T clone(Gson gson, Object o, Class clazz) {
		String json = gson.toJson(o);
		return (T) gson.fromJson(json, clazz);
	}

	@SuppressWarnings("unchecked")
	public static <T> T cloneBySerialize(Object t) throws Exception {
		ByteArrayOutputStream bos = null;
		ObjectInputStream ois = null;
		try {
			bos = new ByteArrayOutputStream();
			serializeToOutputStream(t, bos);
			byte[] bytes = bos.toByteArray();
			ois = new ObjectInputStream(new ByteArrayInputStream(bytes));
			T clone = (T) ois.readObject();

			return clone;
		} finally {
			if (null != bos)
				bos.close();
			if (null != ois)
				ois.close();
		}
	}

	private static void serializeToOutputStream(Object ser, OutputStream os)
			throws IOException {
		ObjectOutputStream oos = null;
		try {
			oos = new ObjectOutputStream(os);
			oos.writeObject(ser);
			oos.flush();
		} finally {
			oos.close();
		}
	}

	public static void main(String[] args) throws Exception {
		String dd = "SSSS";
		String clone = CloneTool.clone(dd, String.class);
		System.out.println(clone);
		System.out.println(CloneTool.cloneBySerialize(dd));
	}
}
