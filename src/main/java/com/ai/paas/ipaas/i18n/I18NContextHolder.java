package com.ai.paas.ipaas.i18n;

import java.util.Locale;
import java.util.TimeZone;

/**
 * 用于存储国际化的Locale和Zone信息，以便在各层使用
 * 
 * @author douxiaofeng
 *
 */
public abstract class I18NContextHolder {

	private static ThreadLocal<TimeZone> timeZoneHolder = new ThreadLocal<>();
	private static ThreadLocal<Locale> localeHolder = new ThreadLocal<>();

	/**
	 * Reset the Time Zone Context for the current thread.
	 */
	public static void resetZoneContext() {
		timeZoneHolder.set(null);
	}

	/**
	 * Reset Locale for the current thread.
	 */
	public static void resetLocaleContext() {
		localeHolder.set(null);
	}

	public static void setZone(TimeZone zone) {
		timeZoneHolder.set(zone);
	}

	public static void setLocale(Locale locale) {
		localeHolder.set(locale);
	}

	public static TimeZone getZone() {
		return timeZoneHolder.get();
	}

	public static Locale getLocale() {
		return localeHolder.get();
	}

	public static void clear() {
		timeZoneHolder.remove();
		timeZoneHolder = null;
		localeHolder.remove();
		localeHolder = null;
	}

}