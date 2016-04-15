package com.ai.paas.ipaas.util;

import java.util.Locale;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ai.paas.ipaas.i18n.MultiResourceBundle;

public class ResourceUtil {
	private static transient final Logger log = LoggerFactory
			.getLogger(ResourceUtil.class);
	private static ResourceBundle rb = null;
	private static Locale locale = null;
	static {
		if (null != System.getProperty("user.locale")) {
			locale = new Locale(System.getProperty("user.locale"));
		} else {
			locale = Locale.CHINA;
		}
		rb = MultiResourceBundle.getBundle(
				"com.ai.paas.ipaas.common.ipaas-common", locale);
	}

	public static void addBundle(String baseName, Locale locale) {
		try {
			if (log.isInfoEnabled()) {
				log.info("Add localization file:" + baseName + ",locale:"
						+ locale + " to i18n!");
			}
			rb = MultiResourceBundle.getBundle(baseName, locale);
		} catch (Exception e) {
			// do not do anything
		}
	}

	public static void addBundle(String baseName) {
		try {
			if (log.isInfoEnabled()) {
				log.info("Add localization file:" + baseName + ",locale:"
						+ locale + " to i18n!");
			}
			rb = MultiResourceBundle.getBundle(baseName, locale);
		} catch (Exception e) {
			// do not do anything
		}
	}

	public static String getMessage(String key, String... params) {
		String message = getMessage(key);
		if (null != params && params.length > 0) {
			StringBuilder tokens = new StringBuilder("\\{[");
			for (int i = 0; i < params.length; i++) {
				tokens.append(i).append("|");
			}
			tokens.deleteCharAt(tokens.length() - 1);
			tokens.append("]\\}");
			Pattern pattern = Pattern.compile(tokens.toString());
			Matcher matcher = pattern.matcher(message);
			StringBuffer sb = new StringBuffer();
			int i = 0;
			while (matcher.find()) {
				matcher.appendReplacement(sb, params[i]);
				i++;
			}
			matcher.appendTail(sb);
			message = sb.toString();
		}
		return message;
	}

	public static String getMessage(String key) {
		try {
			return rb.getString(key);
		} catch (Exception e) {
			// 如果没有取到，则返回key
			return key;
		}
	}

	public static void main(String[] args) {
		System.out.println(ResourceUtil
				.getMessage("com.ai.paas.ipaas.common.srvid_null"));
		String[] values = { "sss", "ssssd", "ggggg", "hhhhh", "iiiiiiii" };
		System.out.println(ResourceUtil.getMessage(
				"com.ai.paas.ipaas.common.test", values));

	}
}
