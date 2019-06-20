package com.ai.paas.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ShellUtil {
    private static final Logger log = LoggerFactory.getLogger(ShellUtil.class);

    public static boolean execCommand(String[] cmd) {
        if (log.isDebugEnabled()) {
            StringBuilder cmds = new StringBuilder();
            for (int i = 0; i < cmd.length; i++) {
                cmds.append(" ").append(cmd[i]);
            }
            log.debug(cmds.toString());
        }
        try {
            Process process = Runtime.getRuntime().exec(cmd);
            return process.waitFor() == 0;
        } catch (Exception e) {
            log.error("", e);
            return false;
        }
    }

    public static boolean execCommand(String cmd) {
        if (log.isDebugEnabled()) {
            log.debug(cmd);
        }
        try {
            Process process = Runtime.getRuntime().exec(cmd);
            return process.waitFor() == 0;
        } catch (Exception e) {
            log.error("", e);
            return false;
        }
    }

    public static void main(String[] args) {
        String[] cmd = new String[] { "keytool", "-genkey", "-validity", "36500", "-keysize", "1024", "-alias", "ss",
                "-keyalg", "RSA", "-keystore", "/Volumes/HD/Downloads/zjy.keystore", "-dname",
                "CN=(SS),OU=(SS),O=(SS),L=(BJ),ST=(BJ),C=(CN)", "-storepass", "123456", "-keypass", "123456", "-v" };
        execCommand(cmd);
    }
}
