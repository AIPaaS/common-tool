package com.ai.paas.model;

import java.io.Serializable;
import java.util.Locale;
import java.util.TimeZone;
import java.util.UUID;

/**
 * 参数基础类. Date: 2017年2月22日 <br>
 * Copyright (c) 2017 asiainfo.com <br>
 * 
 * @author gucl
 */
public class Request extends Page implements Serializable {
	private static final long serialVersionUID = 1L;

	public Request() {
		super();
		init();
	}

	private void init() {
		// 当没有时，自动生成一个。发生在web新建对象时。当到服务提供者端时，已经有了。
		if (null == traceId || "".equals(traceId))
			traceId = UUID.randomUUID().toString().replaceAll("\\-", "").toUpperCase();
		if (null == locale)
			locale = Locale.ENGLISH;

	}

	public Request(Page page) {
		super(page);
		init();
	}

	public Request(int pageNo, int pageSize) {
		super(pageNo, pageSize);
		init();
	}

	/**
	 * traceId，必填
	 */
	private String traceId;

	/**
	 * 租户Id，必填
	 */
	private String tenantId;

	/**
	 * 工号
	 */
	private String operId;

	/**
	 * 省分代码
	 */
	private String provinceCode;

	/**
	 * 地市代码
	 */
	private String cityCode;

	private String countyCode;

	/**
	 * 用户的语言
	 */
	private Locale locale;

	/**
	 * 用户时区
	 */
	private TimeZone timeZone;

	/**
	 * 用户币种
	 */
	private String userCurrency;

	public String getTraceId() {
		return traceId;
	}

	public void setTraceId(String traceId) {
		this.traceId = traceId;
	}

	public String getOperId() {
		return operId;
	}

	public void setOperId(String operId) {
		this.operId = operId;
	}

	public String getProvinceCode() {
		return provinceCode;
	}

	public void setProvinceCode(String provinceCode) {
		this.provinceCode = provinceCode;
	}

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	public String getTenantId() {
		return tenantId;
	}

	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}

	public String getCountyCode() {
		return countyCode;
	}

	public void setCountyCode(String countyCode) {
		this.countyCode = countyCode;
	}

	public Locale getLocale() {
		return locale;
	}

	public void setLocale(Locale locale) {
		this.locale = locale;
	}

	public String getUserCurrency() {
		return userCurrency;
	}

	public void setUserCurrency(String userCurrency) {
		this.userCurrency = userCurrency;
	}

	public TimeZone getTimeZone() {
		return timeZone;
	}

	public void setTimeZone(TimeZone timeZone) {
		this.timeZone = timeZone;
	}

}
