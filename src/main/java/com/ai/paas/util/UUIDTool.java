package com.ai.paas.util;

import java.util.UUID;

import org.apache.commons.text.RandomStringGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UUIDTool {

    private static final Logger log = LoggerFactory.getLogger(UUIDTool.class);

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
        int hashCode = genId32().hashCode();
        if (hashCode == Integer.MIN_VALUE)
            return Integer.MAX_VALUE;
        return Math.abs(hashCode);
    }

    public static void main(String[] args) {
        log.info(UUIDTool.genId32());
        log.info(UUIDTool.genShortId());
        log.info(UUIDTool.genShortId(16));
        log.info(UUIDTool.genShortId(10));
        log.info(UUIDTool.genShortId(6));
        log.info("{}", UUIDTool.getId());
    }
}
