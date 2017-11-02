package com.ai.paas.ipaas.i18n;

public abstract class ZoneContextHolder {

	private static ThreadLocal<String> zoneContextHolder = new ThreadLocal<>();

	/**
	 * Reset the LocaleContext for the current thread.
	 */
	public static void resetZoneContext() {
		zoneContextHolder.set(null);
	}

	public static void setZone(String zone) {
		zoneContextHolder.set(zone);
	}

	public static String getZone() {
		return zoneContextHolder.get();
	}

	public static void clear() {
		zoneContextHolder.remove();
		zoneContextHolder = null;
	}

}