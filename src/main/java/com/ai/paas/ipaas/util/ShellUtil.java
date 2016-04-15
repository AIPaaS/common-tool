package com.ai.paas.ipaas.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ShellUtil {
	private static transient final Logger log = LoggerFactory.getLogger(ShellUtil.class);

	public static boolean execCommand(String[] cmd) {
		if (log.isDebugEnabled()) {
			String cmdStr = "";
			for (int i = 0; i < cmd.length; i++) {
				cmdStr = cmdStr + " " + cmd[i];
			}
			log.debug(cmdStr);
		}
		try {
			Process process = Runtime.getRuntime().exec(cmd);
			if (process.waitFor() == 0) {
				return true;
			} else {
				return false;
			}
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
			if (process.waitFor() == 0) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			log.error("", e);
			return false;
		}
	}

	public static void main(String[] args) {
		String[] cmd = new String[] { "keytool", "-genkey", "-validity",
				"36500", "-keysize", "1024", "-alias", "ss", "-keyalg", "RSA",
				"-keystore", "/Volumes/HD/Downloads/zjy.keystore", "-dname",
				"CN=(SS),OU=(SS),O=(SS),L=(BJ),ST=(BJ),C=(CN)", "-storepass",
				"123456", "-keypass", "123456", "-v" };
		execCommand(cmd);
	}
}
