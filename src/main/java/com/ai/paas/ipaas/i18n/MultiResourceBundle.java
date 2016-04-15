package com.ai.paas.ipaas.i18n;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;

public class MultiResourceBundle extends ResourceBundle {
	protected static final Control CONTROL = new MultiResourceBundleControl();
	private static Properties properties = new Properties();

	public MultiResourceBundle() {

	}

	public MultiResourceBundle(String baseName) {
		setParent(ResourceBundle.getBundle(baseName, CONTROL));
	}

	@Override
	protected Object handleGetObject(String key) {
		return properties != null ? properties.get(key) : parent.getObject(key);
	}
	

	@Override
	@SuppressWarnings("unchecked")
	public Enumeration<String> getKeys() {
		return properties != null ? (Enumeration<String>) properties
				.propertyNames() : parent.getKeys();
	}

	protected static class MultiResourceBundleControl extends Control {
		@Override
		public ResourceBundle newBundle(String baseName, Locale locale,
				String format, ClassLoader loader, boolean reload)
				throws IllegalAccessException, InstantiationException,
				IOException {
			properties.putAll(load(baseName, locale, loader));
			return new MultiResourceBundle();
		}

		private Properties load(String baseName, Locale locale,
				ClassLoader loader) throws IOException {
			Properties properties = new Properties();
			properties.load(loader.getResourceAsStream(baseName + "_"
					+ locale.getDisplayName() + ".properties"));
			return properties;
		}
	}

}