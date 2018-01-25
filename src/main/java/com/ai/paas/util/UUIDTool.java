package com.ai.paas.util;

import java.util.UUID;

import org.apache.commons.text.RandomStringGenerator;

public class UUIDTool {
	private static RandomStringGenerator uuidGenerator = new RandomStringGenerator.Builder().withinRange('0', 'z')
			.filteredBy(t -> t >= '0' && t <= '9', t -> t >= 'A' && t <= 'Z').build();

	private UUIDTool() {

	}

	public static String genId32() {
		return UUID.randomUUID().toString().replaceAll("\\-", "").toUpperCase();
	}

	public static String genShortId() {
		return uuidGenerator.generate(8);
	}

	public static String genShortId(int len) {
		return uuidGenerator.generate(len);
	}

	public static int getId() {
		return Math.abs(genId32().hashCode());
	}

	public static void main(String[] args) {
		System.out.println(UUIDTool.genId32());
		System.out.println(UUIDTool.genShortId());
		System.out.println(UUIDTool.genShortId(16));
		System.out.println(UUIDTool.genShortId(10));
		System.out.println(UUIDTool.genShortId(6));
		System.out.println(UUIDTool.getId());
	}
}
